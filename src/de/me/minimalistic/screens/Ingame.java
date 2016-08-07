package de.me.minimalistic.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import de.me.minimalistic.ingame.World;

public class Ingame implements Screen {
	
	
	Game game;
	World world;
	
	OrthographicCamera camera;
	SpriteBatch spriteBatch;
	Sprite exitmenu[];
	Rectangle hovering[];
	Sound choice;
	
	
	boolean exit = false, exit_yes_hovered = false, exit_no_hovered = false;
	int playing_lvl = 0;
	
	
	
	public Ingame(Game game, int playing_lvl){
		
		this.game = game;
		this.playing_lvl = playing_lvl;
		
	}
	

	@Override
	public void render(float delta) {
		
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
			
			exit = true;
			
		}
		
		if(exit){
			
			exiting();
			return;
			
		}
		
		world.update(delta);
		
		camera.update();
		
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		
			world.render(spriteBatch);
		
		spriteBatch.end();
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		
		if(!Gdx.input.isCursorCatched()) Gdx.input.setCursorCatched(true);
		
		spriteBatch = new SpriteBatch();
		spriteBatch.setShader(SpriteBatch.createDefaultShader());
		
		world = new World(game, playing_lvl);
		
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(false);
		
		
		exitmenu = new Sprite[5];
		
		exitmenu[0] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/exitmenu_bg.png")));
		exitmenu[1] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/exitmenu_yes.png")));
		exitmenu[2] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/exitmenu_yes_backlight.png")));
		exitmenu[3] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/exitmenu_no.png")));
		exitmenu[4] = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/exitmenu_no_backlight.png")));

		hovering = new Rectangle[2];
		
		hovering[0] = new Rectangle(236, 310, 78, 32); // YES button RECT
		hovering[1] = new Rectangle(487, 311, 72, 32); // NO button RECT
		
		choice = Gdx.audio.newSound(Gdx.files.internal("minimalistic_res/fx/menu_click.wav"));
		
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
		
		for(Sprite tmp : exitmenu){
			
			tmp.getTexture().dispose();
			
		}
		
		// OTHER
		world.dispose();
		
		// SOUNDS
		choice.dispose();
		
	}
	
	
	private void exiting(){
		
		camera.update();
		
		spriteBatch.setProjectionMatrix(camera.combined);
		
		if(Gdx.input.isCursorCatched()) Gdx.input.setCursorCatched(false);
		
		spriteBatch.begin();
		
		
			exitmenu[0].draw(spriteBatch);
			
			float cx = Gdx.input.getX(), cy = Gdx.input.getY();
			
			if(hovering[0].contains(cx, cy)){
				
				exitmenu[2].draw(spriteBatch); // yes BACKLIGHT
				
				if(!exit_yes_hovered){
					
					exit_yes_hovered = true;
					choice.play();
					
				}
				
				if(Gdx.input.isButtonPressed(Buttons.LEFT)){
					
					game.setScreen(new MainMenu(game));
					
				}
				
			}else if(exit_yes_hovered){
				
				exit_yes_hovered = false;
				
			}
			
			if(hovering[1].contains(cx, cy)){
				
				exitmenu[4].draw(spriteBatch); // no BACKLIGHT
				
				if(!exit_no_hovered){
					
					exit_no_hovered = true;
					choice.play();
					
				}
				
				if(Gdx.input.isButtonPressed(Buttons.LEFT)){
					
					if(!Gdx.input.isCursorCatched()) Gdx.input.setCursorCatched(true);
					exit = false;
					
				}
				
			}else if(exit_no_hovered){
				
				exit_no_hovered = false;
				
			}
			
			exitmenu[1].draw(spriteBatch); // YES button
			exitmenu[3].draw(spriteBatch); // NO button
		
			
			
		spriteBatch.end();
		
	}

}
