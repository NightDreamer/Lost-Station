package de.me.minimalistic.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class SelectingMenu implements Screen {
	
	Game game;
	SpriteBatch spriteBatch;
	Sprite selectingmenu[];
	Rectangle hovering[];
	Sound click;
	
	Ingame ingame_lvl0;
	
	
	boolean lvl1_hovered = false, lvl2_hovered = false, lvl3_hovered = false, back_hovered = false;
	float alpha = 0.0f;
	
	
	
	public SelectingMenu(Game game){
		
		this.game = game;
		
	}
	
	

	@Override
	public void render(float delta) {

		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		if(alpha < 0.99f){
			
			selectingmenu[0].setColor(1f, 1f, 1f, alpha);
			selectingmenu[1].setColor(1f, 1f, 1f, alpha);
			selectingmenu[2].setColor(1f, 1f, 1f, alpha);
			selectingmenu[3].setColor(1f, 1f, 1f, alpha);
			selectingmenu[4].setColor(1f, 1f, 1f, alpha);
			selectingmenu[5].setColor(1f, 1f, 1f, alpha);
			selectingmenu[6].setColor(1f, 1f, 1f, alpha);
			selectingmenu[7].setColor(1f, 1f, 1f, alpha);
			selectingmenu[8].setColor(1f, 1f, 1f, alpha);
			
			alpha += 0.01f;
			
		}
		
		
		spriteBatch.begin();
		
		selectingmenu[0].draw(spriteBatch);
		
		float cx = Gdx.input.getX(), cy = Gdx.input.getY();
		
		
		if(hovering[0].contains(cx, cy)){
			
			if(!back_hovered){
				
				click.play();
				back_hovered = true;
				
			}
			
			selectingmenu[2].draw(spriteBatch);
			
			if(Gdx.input.isButtonPressed(Buttons.LEFT)){
				
					game.setScreen(new MainMenu(game));					
				
			}
			
		}
		
		
		
		if(hovering[1].contains(cx, cy)){
			
			if(!lvl1_hovered){
				
				click.play();
				lvl1_hovered = true;
				
			}
			
			selectingmenu[4].draw(spriteBatch);
			
			if(Gdx.input.isButtonPressed(Buttons.LEFT)){
				
				game.setScreen(ingame_lvl0);
				
			}
			
		}
		
		
		if(hovering[2].contains(cx, cy)){
			
			if(!lvl2_hovered){
				
				click.play();
				lvl2_hovered = true;
				
			}
			
			selectingmenu[6].draw(spriteBatch);
			
			if(Gdx.input.isButtonPressed(Buttons.LEFT)){
				
				game.setScreen(new Ingame(game, 1));
				
			}
			
		}
		
		
		if(hovering[3].contains(cx, cy)){
			
			if(!lvl3_hovered){
				
				click.play();
				lvl3_hovered = true;
				
			}
			
			selectingmenu[8].draw(spriteBatch);
			
			if(Gdx.input.isButtonPressed(Buttons.LEFT)){
				
				game.setScreen(new Ingame(game, 2));
				
			}
			
		}
		
		
		
		selectingmenu[1].draw(spriteBatch);
		selectingmenu[3].draw(spriteBatch);
		selectingmenu[5].draw(spriteBatch);
		selectingmenu[7].draw(spriteBatch);
	
	
	spriteBatch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {

		// SPRITEBATCH & SPRITES
		spriteBatch = new SpriteBatch();
		spriteBatch.setShader(SpriteBatch.createDefaultShader());
		
		selectingmenu = new Sprite[9];
		
		selectingmenu[0] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/selecting_bg.png")));
		selectingmenu[1] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/highscore_back.png")));
		selectingmenu[2] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/highscore_back_backlight.png")));
		selectingmenu[3] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/selecting_level1.png")));
		selectingmenu[4] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/selecting_level1_backlight.png")));
		selectingmenu[5] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/selecting_level2.png")));
		selectingmenu[6] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/selecting_level2_backlight.png")));
		selectingmenu[7] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/selecting_level3.png")));
		selectingmenu[8] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/selecting_level3_backlight.png")));
		
		// OTHERS
		click = Gdx.audio.newSound(Gdx.files.internal("minimalistic_res/fx/menu_click.wav"));
		
		hovering = new Rectangle[4];
		
		hovering[0] = new Rectangle(675, 542, 87, 32); // BACK button RECT
		hovering[1] = new Rectangle(60, 96, 145, 81); // LEVEL 1 button RECT
		hovering[2] = new Rectangle(240, 96, 145, 81); // LEVEL 2 button RECT
		hovering[3] = new Rectangle(398, 96, 145, 81); // LEVEL 3 button RECT
		
		ingame_lvl0 = new Ingame(game, 0);
		
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
		
		for(Sprite tmp : selectingmenu){
			
			tmp.getTexture().dispose();
			
		}
		
		// OTHERS
		click.dispose();
		
	}

}
