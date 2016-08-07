package de.me.minimalistic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.me.minimalistic.screens.MainMenu;

public class Main extends Game{
	
	
	@Override
	public void create() {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		
		Gdx.input.setCursorCatched(false);
		Gdx.input.setCursorPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		
		setScreen(new MainMenu(this));
		
	}
	
	
	
	
	
	public static void main(String[] args){
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title		= " Minimalistic                                               Ludum Dare 26, 28/29.04.2013, Theme: Minimalism";
		config.width		= 800;
		config.height		= 600;
		config.samples		= 8;
		config.useGL20		= true;
		config.fullscreen	= false;
		config.resizable	= false;
		config.vSyncEnabled = true;
		
		new LwjglApplication(new Main(), config);
		
	}

	

}
