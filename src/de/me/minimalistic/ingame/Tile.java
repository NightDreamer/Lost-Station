package de.me.minimalistic.ingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import de.me.minimalistic.ingame.Player.ForbiddenDirection;

public class Tile {
	
	Sprite tile_img;
	Rectangle bounds;
	Player player;
	Level lvl;
	java.awt.Rectangle tile_bounds = new java.awt.Rectangle(), player_bounds = new java.awt.Rectangle(), intersection = new java.awt.Rectangle();
	
	
	boolean touched = false, fade_out = false;
	float alpha = 0.0f;
	
	
	public Tile(Vector2 position, float tile_size, Player player, Level lvl){
		
		this.player = player;
		this.lvl = lvl;
		
		bounds = new Rectangle(position.x, position.y, tile_size, tile_size);
		
		tile_img = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/ingame_tile.png")));
		tile_img.setSize(tile_size, tile_size);
		tile_img.setPosition(position.x, position.y);
		tile_img.setColor(1f, 1f, 1f, alpha);
		
	}
	
	
	public void update(float delta){
		
		tile_bounds.setBounds((int)bounds.x, (int)(bounds.y + bounds.height), (int)bounds.width, (int)(bounds.height));
		player_bounds.setBounds((int)player.bounds.x, (int)(player.bounds.y + player.bounds.height), (int)player.bounds.width, (int)(player.bounds.height));
		
		
		if(tile_bounds.intersects(player_bounds) && !touched){
				
				player.fails ++;
				touched = true;
				lvl.hit.play();
				
		}
		
		
		if(touched){
			
			if(!fade_out){
				
				if(tile_img.getColor().a < 0.9f){
					tile_img.setColor(1f, 1f, 1f, tile_img.getColor().a + 0.1f);
				}else{
					fade_out = true;
				}
				
			}else{
				
				if(tile_img.getColor().a > 0.1f){
					tile_img.setColor(1f, 1f, 1f, tile_img.getColor().a - 0.1f);
				}else{
					if(tile_img.getColor().a > 0.0f) tile_img.setColor(1f, 1f, 1f, 0.0f);
					fade_out = false;
					touched = false;
				}
				
			}
			
		}
		
	}
	
	
	
	public void colliding(float delta){
		
		// check if COLLIDE
		if(tile_bounds.intersects(player_bounds)){
			
			intersection = tile_bounds.intersection(player_bounds);

			
			if( intersection.width < intersection.height ){	// RIGHT & LEFT
				
				if( (player_bounds.x + player_bounds.width) > (tile_bounds.x + tile_bounds.width) ){
					
					player.addPos(intersection.width, 0f);
					player.forbidden_dir = ForbiddenDirection.LEFT;
					
				}else if( player_bounds.x < tile_bounds.x){
					
					player.subPos(intersection.width + 1f, 0f);
					player.forbidden_dir = ForbiddenDirection.RIGHT;
					
				}
				
			}else{											// TOP & DOWN
				
				if( player_bounds.y > tile_bounds.y ){
					
					player.addPos(0f, intersection.height);
					player.forbidden_dir = ForbiddenDirection.DOWN;
					
				}else if( (player_bounds.y + player_bounds.height) < (tile_bounds.y + tile_bounds.height) ){
					
					player.subPos(0f, intersection.height + 1f);
					player.forbidden_dir = ForbiddenDirection.UP;
					
				}
				
			}
			
		}
		
	}
	
	
	
	public void render(SpriteBatch spriteBatch){
		
		tile_img.draw(spriteBatch);
		
	}
	
	
	public void dispose(){
		
		tile_img.getTexture().dispose();
		player = null;
		lvl = null;
		
	}
	

}
