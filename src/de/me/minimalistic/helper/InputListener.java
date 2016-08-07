package de.me.minimalistic.helper;

import com.badlogic.gdx.Gdx;

public class InputListener {
	
	//keyboard & mouse
	boolean keys[] = new boolean[256];
	
//------------------------------------------------------------------------------
	
	public InputListener(){
		
		for(int i = 0; i < 256; i++){
			keys[i] = false;
		}
		
	}
	
	
// Keyboard --------------------------------------------------------------------
	
	public boolean isKeyPressed(int key){
		
		if(Gdx.input.isKeyPressed(key)){
			if(keys[key]){
				return false;
			}else{
				keys[key] = true;
				return true;
			}
		}else{
			keys[key] = false;
			return false;
		}
		
	}
	
	public boolean isKeyDown(int key){
		
		return Gdx.input.isKeyPressed(key);
		
	}
	
	
}
