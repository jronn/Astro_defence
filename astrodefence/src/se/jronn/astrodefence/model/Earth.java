package se.jronn.astrodefence.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Interpolation;

/**
 * Object represents earth
 * 
 * @author Greedy
 * @since 2014-03-09
 * @version 1.0
 */
public class Earth extends Sprite{

	private float health;
	private float healthPercent;
	private float ammo;
	private float bomb;
	private float bombPercent;
	private int score;
	private Circle collisionCircle;
	private float maxHealth = 1;
	private static final float MAX_AMMO = 1;
	private static final float MAX_BOMB = 100;
	private Preferences prefs;
	
	private float regAmount;
	
	public Earth(TextureRegion texture) {
		super(texture);
				
		prefs = Gdx.app.getPreferences("se.jronn.astrodefence");
		
		health = maxHealth;
		ammo = MAX_AMMO;
		bomb = MAX_BOMB;
		
		regAmount = 0;
		score = 0;
	}
	
	public void addMaxHealth(float amount) {
		maxHealth += amount;
		health = maxHealth;
	}
	
	public void setBombRegen(float reg) {
		regAmount = reg;
	}
	
	public Circle getCollisionCircle() {
		return collisionCircle;
	}
	
	public void setCollisionCircle(Circle circle) {
		collisionCircle = circle;
	}
	
	public float getHealth() {
		return health;
	}
	
	public float getHealthPercent() {
		if(healthPercent < 0 ) {
			return 0;
		}
		else {
			healthPercent = Interpolation.linear.apply(healthPercent, health/maxHealth, 0.1f);
			return healthPercent;
		}
	}
	
	public float getAmmo() {
		return ammo;
	}
	
	public boolean bombPowerFull() {
		if(bomb == MAX_BOMB)
			return true;
		else 
			return false;
	}
	
	public float getBombPercent() {
		if(bombPercent < 0 ) {
			return 0;
		}
		else {
			bombPercent = Interpolation.linear.apply(bombPercent, bomb/MAX_BOMB, 0.1f);
			return bombPercent;
		}
	}
	
	public void addBombPower(float delta) {
		bomb += regAmount * delta;
		if(bomb > MAX_BOMB) bomb = MAX_BOMB;
		if(bomb < 0) bomb = 0;
	}
	
	public void clearBombPower() {
		bomb = 0;
	}
	
	public void addAmmo(float amount) {
		ammo += amount;
		
		if(ammo > MAX_AMMO) ammo = MAX_AMMO;
		if(ammo < 0) ammo = 0;
	}
	
	public void damage(float dmg) {
		health -= dmg;
	}
	
	public void addScore(int score) {
		this.score += score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void reset() {
		health = maxHealth;
		ammo = MAX_AMMO;
		bomb = MAX_BOMB;

		if(prefs.getInteger("bomb") <= 0) {
			clearBombPower();
		}
		
		score = 0;
	}
}
