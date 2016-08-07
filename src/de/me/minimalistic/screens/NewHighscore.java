package de.me.minimalistic.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;

import de.me.minimalistic.helper.InputListener;

public class NewHighscore implements Screen{
	
	Game game;
	SpriteBatch spriteBatch;
	Sprite nameborder, ok, ok_backlight;
	BitmapFont font20, font32, font42;
	InputListener input;
	Rectangle rect;
	Sound click;
	
	String secs, millisecs, new_name = "";
	int fails, lvl;
	float fading_alpha = 0.0f;
	boolean fade_in = true, fade_out = false, ok_hovered = false;
	
	
	public NewHighscore(int fails, String secs, String millisecs, int lvl, Game game){
		
		this.game = game;
		this.lvl = lvl;
		this.fails = fails;
		this.secs = secs;
		this.millisecs = millisecs;
		
	}


	@Override
	public void render(float delta) {

		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(fade_in){
			
			if(fading_alpha < 0.99f){
				
				fading_alpha += 0.01f;
				font20.setColor(1f, 1f, 1f, fading_alpha);
				font32.setColor(1f, 1f, 1f, fading_alpha);
				font42.setColor(1f, 1f, 1f, fading_alpha);
				
			}else{
				
				fade_in = false;
				
			}
			
		}else if(fade_out){
			
			if(fading_alpha > 0.01f){
				
				nameborder.setColor(1f, 1f, 1f, fading_alpha);
				ok.setColor(1f, 1f, 1f, fading_alpha);
				ok_backlight.setColor(1f, 1f, 1f, fading_alpha);
				
				font20.setColor(1f, 1f, 1f, fading_alpha);
				font32.setColor(1f, 1f, 1f, fading_alpha);
				font42.setColor(1f, 1f, 1f, fading_alpha);
				
				fading_alpha -= 0.01f;
				
			}else{
				
				this.saveHighscore();
				game.setScreen(new MainMenu(game));
				
			}

			
		}else if(!fade_in && !fade_out){
			
			if(nameborder.getColor().a < 0.98f) nameborder.setColor(1f, 1f, 1f, nameborder.getColor().a + 0.02f);
			if(ok.getColor().a < 0.98f) ok.setColor(1f, 1f, 1f, ok.getColor().a + 0.02f);
			if(ok_backlight.getColor().a < 0.98f) ok_backlight.setColor(1f, 1f, 1f, ok_backlight.getColor().a + 0.02f);
			
		}
		
		spriteBatch.begin();
		
			nameborder.draw(spriteBatch);
			
			if(rect.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())){
				
				if(!ok_hovered){
					
					click.play();
					ok_hovered = true;
					
				}
				
				if(Gdx.input.isButtonPressed(Buttons.LEFT)){
					
					fade_out = true;
					
				}
				
				ok_backlight.draw(spriteBatch);
				
			}else if(ok_hovered){
				
				ok_hovered = false;
				
			}
		
			ok.draw(spriteBatch);
			
			font20.draw(spriteBatch, "You 've beaten level "+lvl+" in "+secs+"."+millisecs+" seconds !", 64, Gdx.graphics.getHeight() - 64);
			font20.draw(spriteBatch, "Please enter your name (only A-Z, a-z) : ", 256, Gdx.graphics.getHeight() - 128);
			font42.draw(spriteBatch, new_name, 224, Gdx.graphics.getHeight()/2 + 32);
			font32.draw(spriteBatch, "Congratulation", 350, 64);
			
		spriteBatch.end();
		
		if(!fade_in && !fade_out){
			
			getInput();
			
		}
		
	}
	
	private void getInput(){
		
		if(new_name.length() > 11){
			
			if(input.isKeyPressed(Keys.BACKSPACE)){
				if(new_name.length() > 0) new_name = new_name.substring(0, new_name.length()-1);
			}
			
			return;
			
		}

		if(input.isKeyPressed(Keys.BACKSPACE)){
			if(new_name.length() > 0) new_name = new_name.substring(0, new_name.length()-1);
		}
		
		if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)){
			
			if(input.isKeyPressed(Keys.A)) new_name = new_name+"A";
			if(input.isKeyPressed(Keys.B)) new_name = new_name+"B";
			if(input.isKeyPressed(Keys.C)) new_name = new_name+"C";
			if(input.isKeyPressed(Keys.D)) new_name = new_name+"D";
			if(input.isKeyPressed(Keys.E)) new_name = new_name+"E";
			if(input.isKeyPressed(Keys.F)) new_name = new_name+"F";
			if(input.isKeyPressed(Keys.G)) new_name = new_name+"G";
			if(input.isKeyPressed(Keys.H)) new_name = new_name+"H";
			if(input.isKeyPressed(Keys.I)) new_name = new_name+"I";
			if(input.isKeyPressed(Keys.J)) new_name = new_name+"J";
			if(input.isKeyPressed(Keys.K)) new_name = new_name+"K";
			if(input.isKeyPressed(Keys.L)) new_name = new_name+"L";
			if(input.isKeyPressed(Keys.M)) new_name = new_name+"M";
			if(input.isKeyPressed(Keys.N)) new_name = new_name+"N";
			if(input.isKeyPressed(Keys.O)) new_name = new_name+"O";
			if(input.isKeyPressed(Keys.P)) new_name = new_name+"P";
			if(input.isKeyPressed(Keys.Q)) new_name = new_name+"Q";
			if(input.isKeyPressed(Keys.R)) new_name = new_name+"R";
			if(input.isKeyPressed(Keys.S)) new_name = new_name+"S";
			if(input.isKeyPressed(Keys.T)) new_name = new_name+"T";
			if(input.isKeyPressed(Keys.U)) new_name = new_name+"U";
			if(input.isKeyPressed(Keys.V)) new_name = new_name+"V";
			if(input.isKeyPressed(Keys.W)) new_name = new_name+"W";
			if(input.isKeyPressed(Keys.X)) new_name = new_name+"X";
			if(input.isKeyPressed(Keys.Y)) new_name = new_name+"Y";
			if(input.isKeyPressed(Keys.Z)) new_name = new_name+"Z";
			
		}else{
			
			if(input.isKeyPressed(Keys.A)) new_name = new_name+"a";
			if(input.isKeyPressed(Keys.B)) new_name = new_name+"b";
			if(input.isKeyPressed(Keys.C)) new_name = new_name+"c";
			if(input.isKeyPressed(Keys.D)) new_name = new_name+"d";
			if(input.isKeyPressed(Keys.E)) new_name = new_name+"e";
			if(input.isKeyPressed(Keys.F)) new_name = new_name+"f";
			if(input.isKeyPressed(Keys.G)) new_name = new_name+"g";
			if(input.isKeyPressed(Keys.H)) new_name = new_name+"h";
			if(input.isKeyPressed(Keys.I)) new_name = new_name+"i";
			if(input.isKeyPressed(Keys.J)) new_name = new_name+"j";
			if(input.isKeyPressed(Keys.K)) new_name = new_name+"k";
			if(input.isKeyPressed(Keys.L)) new_name = new_name+"l";
			if(input.isKeyPressed(Keys.M)) new_name = new_name+"m";
			if(input.isKeyPressed(Keys.N)) new_name = new_name+"n";
			if(input.isKeyPressed(Keys.O)) new_name = new_name+"o";
			if(input.isKeyPressed(Keys.P)) new_name = new_name+"p";
			if(input.isKeyPressed(Keys.Q)) new_name = new_name+"q";
			if(input.isKeyPressed(Keys.R)) new_name = new_name+"r";
			if(input.isKeyPressed(Keys.S)) new_name = new_name+"s";
			if(input.isKeyPressed(Keys.T)) new_name = new_name+"t";
			if(input.isKeyPressed(Keys.U)) new_name = new_name+"u";
			if(input.isKeyPressed(Keys.V)) new_name = new_name+"v";
			if(input.isKeyPressed(Keys.W)) new_name = new_name+"w";
			if(input.isKeyPressed(Keys.X)) new_name = new_name+"x";
			if(input.isKeyPressed(Keys.Y)) new_name = new_name+"y";
			if(input.isKeyPressed(Keys.Z)) new_name = new_name+"z";
			
		}

		
	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void show() {
		
		if(Gdx.input.isCursorCatched()) Gdx.input.setCursorCatched(false);

		// SPRITEBATCH & SPRITES
		spriteBatch = new SpriteBatch();
		spriteBatch.setShader(SpriteBatch.createDefaultShader());
		
		nameborder = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/newhighscore_nameborder.png")));
		nameborder.setSize(800f, 600f);
		nameborder.setColor(1f, 1f, 1f, 0f);
		
		ok = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/newhighscore_ok.png")));
		ok.setColor(1f, 1f, 1f, 0f);
		
		ok_backlight = new Sprite(new Texture(Gdx.files.internal("minimalistic_res/textures/newhighscore_ok_backlight.png")));
		ok_backlight.setColor(1f, 1f, 1f, 0f);
		
		// FONT
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("minimalistic_res/font/SEGOESCB.TTF"));
		
		font20 = gen.generateFont(20);
		font32 = gen.generateFont(32);
		font42 = gen.generateFont(42);
		
//		font20 = new BitmapFont();
//		font32 = new BitmapFont();
//		font42 = new BitmapFont();
		
		font20.setColor(1f, 1f, 1f, fading_alpha);
		font32.setColor(1f, 1f, 1f, fading_alpha);
		font42.setColor(1f, 1f, 1f, fading_alpha);
		
		gen.dispose();
		
		// OTHERS
		input = new InputListener();
		rect = new Rectangle(366, Gdx.graphics.getHeight() - 439, 66, 35);
		click = Gdx.audio.newSound(Gdx.files.internal("minimalistic_res/fx/menu_click.wav"));
		
	}
	
	
	private void saveHighscore(){
		
		String names[], seconds[], milliseconds[];
		int fails[], lvl[], currentPlace = 0;
		
		// LOADING
		Preferences prefs = Gdx.app.getPreferences("data");
		
		names = new String[6];
		
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
		
		// CHECKING
		for(int i = 0; i < 6; i++){
			
			if(lvl[i] > this.lvl){
				
				currentPlace++;
				
			}else if(lvl[i] == this.lvl){
				
				if(fails[i] < this.fails){
					
					currentPlace++;
					
				}else if(fails[i] == this.fails){
					
					if(seconds[i].compareTo(this.secs) < 0){
						
						currentPlace++;
						
					}else if(seconds[i].compareTo(this.secs) == 0){
						
						if(milliseconds[i].compareTo(this.millisecs) < 0){
							
							currentPlace++;
						
						}else if(milliseconds[i].compareTo(this.millisecs) == 0){
							
							currentPlace++;
							
						}
					
					}
				}
				
			}
			
		}
		
		// SORTING
		if(currentPlace > -1 && currentPlace < 6){
			
			for(int i = 5; i > -1; i--){
				
				if(i == currentPlace){
					
					names[i] = new_name;
					lvl[i] = this.lvl;
					fails[i] = this.fails;
					seconds[i] = this.secs;
					milliseconds[i] = this.millisecs;
					
					break;
					
				}
				
				names[i] = names[i-1];
				lvl[i] = lvl[i-1];
				fails[i] = fails[i-1];
				seconds[i] = seconds[i-1];
				milliseconds[i] = milliseconds[i-1];
				
			}
			
		}
		
		// SAVING
		
		for(int i = 0; i < 6; i++){
			
			prefs.putString((i+1)+"_name", names[i]);
			prefs.putInteger((i+1)+"_lvl", lvl[i]);
			prefs.putInteger((i+1)+"_fails", fails[i]);
			prefs.putString((i+1)+"_seconds", seconds[i]);
			prefs.putString((i+1)+"_millis", milliseconds[i]);
			
		}
		
		prefs.flush();
		
		
		
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
		
		nameborder.getTexture().dispose();
		ok.getTexture().dispose();
		ok_backlight.getTexture().dispose();
		
		// FONTS
		font20.dispose();
		font32.dispose();
		font42.dispose();
		
		// OTHERS
		click.dispose();
		
	}
	
	

}
