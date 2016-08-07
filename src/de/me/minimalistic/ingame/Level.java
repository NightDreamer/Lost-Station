package de.me.minimalistic.ingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Level {
	
	private static final float TILESIZE = 50.0f;
	
	Array<Tile> tiles;
	Player player;
	Goal goal;
	Sound hit;
	
	boolean completed = false;
	
	
	public Level(int level){
		
		hit = Gdx.audio.newSound(Gdx.files.internal("minimalistic_res/fx/hit.wav"));
		
		tiles = new Array<Tile>();
		player = new Player(new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), TILESIZE);
		
		this.loadLevel(level);
		
	}
	
	private void loadLevel(int level){
		
		Pixmap tmp = new Pixmap(Gdx.files.internal("minimalistic_res/textures/level/level_"+level+".png"));
		for(int x = 0; x < tmp.getWidth(); x++){
			
			for(int y = 0; y < tmp.getHeight(); y++){
				
				if(tmp.getPixel(x, y) == Color.rgba8888(Color.WHITE)){
					
					tiles.add(new Tile(new Vector2(x*TILESIZE, Gdx.graphics.getHeight() - ((y+1)*TILESIZE)), TILESIZE, player, this));
					
				}else if(tmp.getPixel(x, y) == Color.rgba8888(Color.BLUE)){
					
					player.setPosition(new Vector2((x*TILESIZE), Gdx.graphics.getHeight() - ((y+1)*TILESIZE)));
					
				}else if(tmp.getPixel(x, y) == Color.rgba8888(Color.RED)){
										
					if(tmp.getPixel(x+1, y) == Color.rgba8888(Color.RED)){
						
						goal = new Goal(new Vector2((x*TILESIZE), Gdx.graphics.getHeight() - ((y+1)*TILESIZE)), TILESIZE*2, TILESIZE, player, this);
						
					} else if(tmp.getPixel(x, y+1) == Color.rgba8888(Color.RED)){
						
						goal = new Goal(new Vector2((x*TILESIZE), Gdx.graphics.getHeight() - ((y+1)*TILESIZE)), TILESIZE, TILESIZE*2, player, this);
							
					}
					
				}
				
			}
			
		}
		
	}
	
	public void update(float delta){
		
		// COMPLETED ?
		goal.update(delta);
		
		// PLAYER
		player.update(delta);
		
		// LIGHT
		for(Tile tmp: tiles){
			
			tmp.update(delta);
			
		}
		
		// COLLISION
		for(Tile tmp : tiles){
			
			tmp.colliding(delta);
			
		}
		
	}
	
	
	public void render(SpriteBatch spriteBatch){
		
		for(Tile tmp: tiles){
			
			tmp.render(spriteBatch);
			
		}
		
		player.render(spriteBatch);
		
	}
	
	
	public void dispose(){
		
		// OTHER
		player.dispose();
		
		for(Tile tmp : tiles){
			
			tmp.dispose();
			
		}
		
		hit.dispose();
		
		goal.dispose();
		
	}
	
	public int getFails(){
		
		return player.fails;
		
	}
	
	public Player getPlayer(){
		
		return player;
		
	}
	
	
	

}
