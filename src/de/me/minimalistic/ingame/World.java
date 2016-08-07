package de.me.minimalistic.ingame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import de.me.minimalistic.screens.NewHighscore;


public class World {
	
	Game game;
	Level level;
	Sprite intro_img;
	BitmapFont font;
	
	int actual_level = 0;
	boolean intro = true, fading_to_gameplay = false, start_time = false;
	float secs = 0f, millisecs = 0f, completed_fading_alpha = 1.0f;
	long startTime = 0L, sleepTime = 0L;
	
	
	
	
	public World(Game game, int actual_level){
		
		this.game = game;
		this.actual_level = actual_level;
		
		// SPRITE
		intro_img = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/ingame_intro.png")));
		intro_img.setColor(1f, 1f, 1f, 0.0f);
		
		// FONT loading
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("minimalistic_res/font/SEGOESCB.TTF"));
		
		font = gen.generateFont(20);
//		font = new BitmapFont();
		
		gen.dispose();
		
		// LEVEL loading
		level = new Level(actual_level);
		
		
		
	}
	
	
	public void update(float delta){
		
		if(intro){
			
			if(intro_img.getColor().a < 0.99f){
				
				intro_img.setColor(1f, 1f, 1f, intro_img.getColor().a + 0.01f);
				
			}else{
				
				fading_to_gameplay = true;
				
			}
			
			if(intro_img.getColor().a > 0.01f && fading_to_gameplay){
				
				intro_img.setColor(1f, 1f, 1f, intro_img.getColor().a - 0.01f);
				
			}else if(fading_to_gameplay){
				
				intro = false;
				
			}
			
			return;
			
		}
		
		// LEVEL COMPLETE -> fade out
		if(level.completed){
			
			// FADE OUT player
			Player player = level.getPlayer();
			if(!player.completed) player.completed = true;
			player.player_img.setColor(1f, 1f, 1f, completed_fading_alpha);
			
			// FADE OUT font
			font.setColor(1f, 1f, 1f, completed_fading_alpha);
			
			// controll alpha
			if(completed_fading_alpha > 0.01f){
				
				completed_fading_alpha -= 0.01f;
				
			}else{
				
				String s = String.valueOf(secs), m = String.valueOf(millisecs);
				game.setScreen(new NewHighscore(player.fails, s.substring(0, s.indexOf(".")), m.substring(m.indexOf(".") - 1, m.indexOf(".")), actual_level+1, game));
				
			}
			
			return;
			
		}
		
		
		
		if(level.getPlayer().started){
			
			start_time = true;
			
		}
		
		if(start_time){
			
			if(startTime == 0L) startTime = System.nanoTime();
			
			Long newTime = System.nanoTime() - startTime;
			
			newTime /= 1000L;
			newTime /= 1000L;
			
			millisecs = newTime;
			
			newTime /= 1000L;
			
			secs = newTime;
		}
		
		level.update(delta);		
		
	}
	
	
	public void render(SpriteBatch spriteBatch){
		
		if(intro){
			
			intro_img.draw(spriteBatch);
			
			return;
			
		}
		
		
		level.render(spriteBatch);
		
		// FAIL AMOUNT
		font.draw(spriteBatch, "Fails : "+level.getFails(), 12, Gdx.graphics.getHeight() - 15);
		
		// LEVEL
		font.draw(spriteBatch, "Level "+(actual_level+1), 325, Gdx.graphics.getHeight() - 15);
		
		// TIME AMOUNT
		String s = String.valueOf(secs), m = String.valueOf(millisecs);
		
		if(m.length() < 5){
			
			font.draw(spriteBatch, "Time : "+s.substring(0, s.indexOf("."))+"."+m.substring(m.indexOf(".") - 1, m.indexOf("."))+" secs", 575, Gdx.graphics.getHeight() - 15);
			
		}else{
			
			font.draw(spriteBatch, "Time : "+s.substring(0, s.indexOf("."))+"."+m.substring(m.indexOf(".") - 3, m.indexOf(".") - 1)+" secs", 575, Gdx.graphics.getHeight() - 15);
			
		}
		
	}	
	
	public void dispose(){
		
		// LEVEL
		level.dispose();
		
		// SPRITES
		intro_img.getTexture().dispose();
		
		// OTHERS
		font.dispose();
		
	}
	
	

}
