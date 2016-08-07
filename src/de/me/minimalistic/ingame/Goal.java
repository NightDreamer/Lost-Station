
package de.me.minimalistic.ingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Goal {
	
	Rectangle bounds;
	Player player;
	Level lvl;
	Sound complete;
	
	
	
	public Goal(Vector2 pos, float width, float height, Player player, Level lvl){
		
		this.player = player;
		this.lvl = lvl;
		
		complete = Gdx.audio.newSound(Gdx.files.internal("minimalistic_res/fx/complete.wav"));
		bounds = new Rectangle(pos.x, pos.y, width, height);
		
	}
	
	public void update(float delta){
		
		if(bounds.overlaps(player.bounds)){
			
			complete.play();
			lvl.completed = true;
			
		}
		
	}
	
	public void dispose(){
		
		complete.dispose();
		
	}
	

}
