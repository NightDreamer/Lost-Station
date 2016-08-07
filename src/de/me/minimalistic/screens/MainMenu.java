package de.me.minimalistic.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MainMenu implements Screen{
	
	
	Game game;
	
	SpriteBatch spriteBatch;
	Sprite mainmenu[];
	Rectangle hovering[];
	Sound click;
	
	boolean selecting_fadeout = false, start_game = false, start_hovered = false, exit_hovered = false, highscore_hovered = false, show_highscore = false;
	float alpha = 0.0f, select_alpha = 0.0f;
	
	
	public MainMenu(Game game){
		
		this.game = game;
		
	}
	
	

	@Override
	public void render(float delta) {
		
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE) && !start_game){
			
			Gdx.app.exit();
			
		}

		
		
		if(alpha < 1.0f && !start_game && !show_highscore){
			
			for(Sprite tmp : mainmenu){
				
				tmp.setColor(1f, 1f, 1f, alpha);
				
			}
			
			alpha += 0.01f;
			
		}
		
		
		
		if(start_game){
			
			if(alpha > 0.01f){
				
				for(Sprite tmp : mainmenu){
					
					tmp.setColor(1f, 1f, 1f, alpha);
					
				}
				
				alpha -= 0.01f;
				
			}else{
				
				game.setScreen(new SelectingMenu(game));
				
			}
			
		}
		
		if(show_highscore){
			
			if(alpha > 0.01f){
				
				for(Sprite tmp : mainmenu){
					
					tmp.setColor(1f, 1f, 1f, alpha);
					
				}
				
				alpha -= 0.01f;
				
			}else{
				
				game.setScreen(new Highscore(game));
				
			}
			
		}
		
		spriteBatch.begin();
		
		
		
			mainmenu[0].draw(spriteBatch);
			
			float cx = Gdx.input.getX(), cy = Gdx.input.getY();
			
			if(hovering[0].contains(cx, cy)){
				
				mainmenu[2].draw(spriteBatch); // start BACKLIGHT
				
				if(!start_hovered){
					
					start_hovered = true;
					click.play();
					
				}
				
				// INTERACT start
				if(Gdx.input.isButtonPressed(Buttons.LEFT)){
					
					start_game = true;
					
				}
				
			}else if(start_hovered){
				
				start_hovered = false;
				
			}
			
			if(hovering[1].contains(cx, cy)){
				
				mainmenu[4].draw(spriteBatch); // exit BACKLIGHT
				
				if(!exit_hovered){
					
					exit_hovered = true;
					click.play();
					
				}
				
				// INTERACT exit
				if(Gdx.input.isButtonPressed(Buttons.LEFT)){
					
					Gdx.app.exit();
					
				}
				
			}else{
				
				if(exit_hovered) exit_hovered = false;
				
			}
			
			
			if(hovering[2].contains(cx, cy)){
				
				mainmenu[6].draw(spriteBatch); // HIGHSCORE BACKLIGHT
				
				if(!highscore_hovered){
					
					highscore_hovered = true;
					click.play();
					
				}
				
				// INTERACT highscore
				if(Gdx.input.isButtonPressed(Buttons.LEFT)){
					
					show_highscore = true;
					
				}
				
			}else{
				
				if(highscore_hovered) highscore_hovered = false;
				
			}
			
			
			mainmenu[1].draw(spriteBatch); // START button
			mainmenu[3].draw(spriteBatch); // EXIT button
			mainmenu[5].draw(spriteBatch); // HIGHSCORE button
			
			
		spriteBatch.end();
		
		
		
	}
	
	public void selectLevel(){
			
			
		
		
	}
	

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		
		spriteBatch = new SpriteBatch();
		spriteBatch.setShader(SpriteBatch.createDefaultShader());
		
		mainmenu = new Sprite[7];
		
		mainmenu[0] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/mainmenu_bg.png")));
		mainmenu[1] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/mainmenu_start.png")));
		mainmenu[2] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/mainmenu_start_backlight.png")));
		mainmenu[3] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/mainmenu_exit.png")));
		mainmenu[4] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/mainmenu_exit_backlight.png")));
		mainmenu[5] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/mainmenu_highscore.png")));
		mainmenu[6] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/mainmenu_highscore_backlight.png")));

		hovering = new Rectangle[3];
		
		hovering[0] = new Rectangle(57, 136, 93, 32); // START button RECT
		hovering[1] = new Rectangle(165, 505, 99, 32); // EXIT button RECT
		hovering[2] = new Rectangle(75, 317, 170, 32); // HIGHSCORE button RECT
		
		
		click = Gdx.audio.newSound(Gdx.files.internal("minimalistic_res/fx/menu_click.wav"));
		
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
		
		for(Sprite tmp : mainmenu){
			
			tmp.getTexture().dispose();
			
		}		
		
		// SOUND
		click.dispose();
		
	}
	
	

}
