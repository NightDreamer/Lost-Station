package de.me.minimalistic.ingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
	
	public enum ForbiddenDirection{
		
		UP, DOWN, LEFT, RIGHT, NOTHING
		
	}
	
	private static final float STEP = 100f;
	
	Sprite player_img;
	Rectangle bounds;
	ForbiddenDirection forbidden_dir = ForbiddenDirection.NOTHING;
	
	int fails = 0;
	boolean started = false, completed = false;
	
	
	
	public Player(Vector2 position, float player_size){
		
		bounds = new Rectangle(position.x, position.y, player_size, player_size);
		
		player_img = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/ingame_player.png")));
		player_img.setSize(player_size, player_size);
		player_img.setPosition(position.x, position.y);
		player_img.setColor(1f, 1f, 1f, 0.0f);
		
	}
	
	
	public void update(float delta){
		
		if(Gdx.input.isKeyPressed(Keys.W)){				// UP
			
			if(forbidden_dir != ForbiddenDirection.UP){
				
				bounds.setY(bounds.y + (delta*STEP));
				forbidden_dir = ForbiddenDirection.NOTHING;
				
				if(!started) started = true;
				
			}
			
		}else if(Gdx.input.isKeyPressed(Keys.S)){		// DOWN
			
			if(forbidden_dir != ForbiddenDirection.DOWN){
				
				bounds.setY(bounds.y - (delta*STEP));
				forbidden_dir = ForbiddenDirection.NOTHING;
				
				if(!started) started = true;
				
			}
			
		}else if(Gdx.input.isKeyPressed(Keys.A)){		// LEFT
			
			if(forbidden_dir != ForbiddenDirection.LEFT){
				
				bounds.setX(bounds.x - (delta*STEP));
				forbidden_dir = ForbiddenDirection.NOTHING;
				
				if(!started) started = true;
				
			}
			
		}else if(Gdx.input.isKeyPressed(Keys.D)){		// RIGHT
			
			if(forbidden_dir != ForbiddenDirection.RIGHT){
				
				bounds.setX(bounds.x + (delta*STEP));
				forbidden_dir = ForbiddenDirection.NOTHING;
				
				if(!started) started = true;
				
			}
			
		}else{
			
			forbidden_dir = ForbiddenDirection.NOTHING;
			
		}
		
		// IMAGE SET TO BOUNDS
		player_img.setPosition(bounds.x, bounds.y);
		
		// IMG COLOR
		if(!completed && player_img.getColor().a < 0.9f) player_img.setColor(1f, 1f, 1f, player_img.getColor().a + 0.1f);
		
	}
	
	
	public void render(SpriteBatch spriteBatch){
		
		player_img.draw(spriteBatch);
		
	}
	
	
	public void dispose(){
		
		// SPRITE
		player_img.getTexture().dispose();
		
		
	}
	
	
	public Vector2 getCenter(){
		
		return new Vector2(bounds.x + (bounds.width/2), bounds.y + (bounds.height/2));
		
	}
	
	public void addFail(){
		
		fails ++;
		
	}
	
	public int getFails(){
		
		return fails;
		
	}
	
	
	public void addPos(float x, float y){
		
		bounds.setX(bounds.x + x);
		bounds.setY(bounds.y + y);
		
		player_img.setX(bounds.x);
		player_img.setY(bounds.y);
		
	}
	
	public void subPos(float x, float y){
		
		bounds.setX(bounds.x - x);
		bounds.setY(bounds.y - y);
		
		player_img.setX(bounds.x);
		player_img.setY(bounds.y);
		
	}
	
	
	public void setPosition(Vector2 pos){
		
		bounds.setX(pos.x);
		bounds.setY(pos.y);
		
		player_img.setX(bounds.x);
		player_img.setY(bounds.y);
		
	}
	

}
