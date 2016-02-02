package se.jronn.astrodefence.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * Object represents a rocket
 * 
 * @author Greedy
 * @since 2014-03-06
 * @version 1.0
 */
public class Rocket extends Sprite{
	
	private float velX;
	private float velY;
	private float moveSpeed;
	
	public Rocket (TextureRegion texture, Sprite target, float x, float y, float width, float height) {
		super(texture);
		super.setPosition(x, y);
		super.setSize(width, height);
		
		Preferences prefs = Gdx.app.getPreferences("se.jronn.astrodefence");
		
		if(prefs.getInteger("missile") < 6) {
			moveSpeed = (Gdx.graphics.getHeight()/10) + (prefs.getInteger("missile") * Gdx.graphics.getHeight()/50);
		}else {
			moveSpeed = (Gdx.graphics.getHeight()/10) + (1 * Gdx.graphics.getHeight()/50);
		}
		
		
		float angle = (float)Math.atan2((target.getY() + target.getHeight()/2 - getHeight()/2)-y, (target.getX() + target.getWidth()/2 - getWidth()/2)-x);
		velX = moveSpeed * (float)Math.cos(angle);
		velY = moveSpeed * (float)Math.sin(angle);

		super.setOrigin(getWidth()/2, getHeight()/2);
		super.rotate((float)Math.toDegrees(angle)-90);
	}
	
	public void move(float delta) {
		super.setPosition(getX() + velX * delta, getY() + velY * delta);
	}
}
