package de.me.minimalistic.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;

public class Highscore implements Screen{
	
	Game game;
	SpriteBatch spriteBatch;
	Sprite back[];
	String names[], seconds[], milliseconds[];
	Rectangle back_bounds;
	BitmapFont font16, font32, font42;
	Sound click;
	
	boolean fade_in = true, fade_out = false, back_hovered = false;
	float alpha = 0.0f;
	int fails[], lvl[];
	
	
	
	public Highscore(Game game){
		
		this.game = game;
		
	}
	
	
	

	@Override
	public void render(float delta) {

		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(fade_in){
			
			if(alpha < 0.99f){
				
				back[0].setColor(1f, 1f, 1f, alpha);
				back[1].setColor(1f, 1f, 1f, alpha);
				
				font16.setColor(1f, 1f, 1f, alpha);
				font32.setColor(1f, 1f, 1f, alpha);
				font42.setColor(1f, 1f, 1f, alpha);
				
				alpha += 0.01f;
				
			}else{
				
				fade_in = false;
				
			}
			
		}else if(fade_out){
			
			if(alpha > 0.01f){
				
				back[0].setColor(1f, 1f, 1f, alpha);
				back[1].setColor(1f, 1f, 1f, alpha);
				
				font16.setColor(1f, 1f, 1f, alpha);
				font32.setColor(1f, 1f, 1f, alpha);
				font42.setColor(1f, 1f, 1f, alpha);
				
				alpha -= 0.01f;
				
			}else{
				
				game.setScreen(new MainMenu(game));
				
			}
			
		}
		
		spriteBatch.begin();
		
			if(back_bounds.contains(Gdx.input.getX(), Gdx.input.getY())){
				
				if(!back_hovered){
					
					click.play();
					back_hovered = true;
					
				}
				
				back[0].draw(spriteBatch);
				
				if(Gdx.input.isButtonPressed(Buttons.LEFT)){
					
					fade_out = true;
					
				}
				
			}else{
				
				if(back_hovered) back_hovered = false;
				
			}
		
			back[1].draw(spriteBatch);
		
			// DRAW HIGHSCORE
			font16.draw(spriteBatch, "Name", 85, Gdx.graphics.getHeight() - 132);
			font16.draw(spriteBatch, "Level", 256, Gdx.graphics.getHeight() - 132);
			font16.draw(spriteBatch, "Fails", 448, Gdx.graphics.getHeight() - 132);
			font16.draw(spriteBatch, "Time", 588, Gdx.graphics.getHeight() - 132);
			
			font16.draw(spriteBatch, "1 . ", 32, Gdx.graphics.getHeight() - 192);
			font16.draw(spriteBatch, "2 . ", 32, Gdx.graphics.getHeight() - 234);
			font16.draw(spriteBatch, "3 . ", 32, Gdx.graphics.getHeight() - 276);
			font16.draw(spriteBatch, "4 . ", 32, Gdx.graphics.getHeight() - 318);
			font16.draw(spriteBatch, "5 . ", 32, Gdx.graphics.getHeight() - 360);
			font16.draw(spriteBatch, "6 . ", 32, Gdx.graphics.getHeight() - 402);
			
			for(int i = 0; i < 6; i++){
				
				font16.draw(spriteBatch, names[i], 85, Gdx.graphics.getHeight() - 192 - (i*42));
				font16.draw(spriteBatch, ""+lvl[i], 256, Gdx.graphics.getHeight() - 192 - (i*42));
				font16.draw(spriteBatch, ""+fails[i], 448, Gdx.graphics.getHeight() - 192 - (i*42));
				font16.draw(spriteBatch, ""+seconds[i]+"."+milliseconds[i], 588, Gdx.graphics.getHeight() - 192 - (i*42));
				
			}
			
			
			font42.draw(spriteBatch, "Highscore", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight() - 32);
			
		spriteBatch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		
		// SPRITEBATCH
		spriteBatch = new SpriteBatch();
		spriteBatch.setShader(SpriteBatch.createDefaultShader());
		
		back = new Sprite[2];
		
		back[0] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/highscore_back_backlight.png")));
		back[0].setColor(1f, 1f, 1f, 0f);
		back[1] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/highscore_back.png")));
		back[1].setColor(1f, 1f, 1f, 0f);

		// FONTS
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("minimalistic_res/font/SEGOESCB.TTF"));
		
		font16 = gen.generateFont(16);
		font32 = gen.generateFont(32);
		font42 = gen.generateFont(42);
//		font16 = new BitmapFont();
//		font32 = new BitmapFont();
//		font42 = new BitmapFont();
//		
		font16.setColor(1f, 1f, 1f, 0.0f);
		font32.setColor(1f, 1f, 1f, 0.0f);
		font42.setColor(1f, 1f, 1f, 0.0f);
		
		gen.dispose();
		
		// OTHERS
		click = Gdx.audio.newSound(Gdx.files.internal("minimalistic_res/fx/menu_click.wav"));
		
		back_bounds = new Rectangle(675, 542, 87, 32);
		
		
		// LOAD HIGHSCORE
		this.loadHighscore();
		
	}
	
	
	public void loadHighscore(){
		
		Preferences prefs = Gdx.app.getPreferences("data");
		
		names = new String[6];
		for(int i = 0; i < 6; i++) names[i] = "";
		
		names[0] = prefs.getString("1_name");
		names[1] = prefs.getString("2_name");
		names[2] = prefs.getString("3_name");
		names[3] = prefs.getString("4_name");
		names[4] = prefs.getString("5_name");
		names[5] = prefs.getString("6_name");
		
		fails = new int[6];
		
		fails[0] = prefs.getInteger("1_fails");
		fails[1] = prefs.getInteger("2_fails");
		fails[2] = prefs.getInteger("3_fails");
		fails[3] = prefs.getInteger("4_fails");
		fails[4] = prefs.getInteger("5_fails");
		fails[5] = prefs.getInteger("6_fails");
		
		seconds = new String[6];
		
		seconds[0] = prefs.getString("1_seconds");
		seconds[1] = prefs.getString("2_seconds");
		seconds[2] = prefs.getString("3_seconds");
		seconds[3] = prefs.getString("4_seconds");
		seconds[4] = prefs.getString("5_seconds");
		seconds[5] = prefs.getString("6_seconds");
		
		milliseconds = new String[6];
		
		milliseconds[0] = prefs.getString("1_millis");
		milliseconds[1] = prefs.getString("2_millis");
		milliseconds[2] = prefs.getString("3_millis");
		milliseconds[3] = prefs.getString("4_millis");
		milliseconds[4] = prefs.getString("5_millis");
		milliseconds[5] = prefs.getString("6_millis");
		
		lvl = new int[6];
		
		lvl[0] = prefs.getInteger("1_lvl");
		lvl[1] = prefs.getInteger("2_lvl");
		lvl[2] = prefs.getInteger("3_lvl");
		lvl[3] = prefs.getInteger("4_lvl");
		lvl[4] = prefs.getInteger("5_lvl");
		lvl[5] = prefs.getInteger("6_lvl");
		
		for(int i = 0; i < 6; i++){
			
			if(names[i].length() == 0) names[i] = "NoOne";
			if(seconds[i].length() == 0) seconds[i] = "0";
			if(milliseconds[i].length() == 0) milliseconds[i] = "0";
			if(String.valueOf(lvl[i]).length() == 0) lvl[i] = 0;
			if(String.valueOf(fails[i]).length() == 0) fails[i] = 0;
			
		}
		
	}
	
	

	@Override
	public void hide() {

		this.dispose();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {

		// SPRITEBATCH & SPRITES
		spriteBatch.dispose();
		
		for(int i = 0; i < back.length; i++) back[i].getTexture().dispose();
		
		// FONTS
		font16.dispose();
		font32.dispose();
		font42.dispose();
		
		// OTHERS
		click.dispose();
		
	}
	
	
	
	
	
	

}
