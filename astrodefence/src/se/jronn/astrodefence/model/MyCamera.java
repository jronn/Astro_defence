package se.jronn.astrodefence.model;

import java.util.Random;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class MyCamera extends OrthographicCamera {
	
	private float timer;
	private float shakeTime;
	private float centerX;
	private float centerY;
	private Boolean shaking;
	private Random rand;
	private float radius;
	private float angle;
	private float offsetX;
	private float offsetY;
	
	public MyCamera(float width, float height) {
		super(width, height);
		centerX = width/2;
		centerY = height/2;
		timer = 0;
		shaking = false;
		rand = new Random();
	}
	
	public void shake(float time, float radius) {
		timer = 0;
		super.position.x = centerX;
		super.position.y = centerY;
		this.radius = radius;
		shakeTime = time;
		angle = rand.nextInt(360);
		shaking = true;		
	}
	
	public void updateShake(float delta) {
		
		if(shaking) {
			timer += delta;
			if(timer > shakeTime) {
				super.position.x = centerX;
				super.position.y = centerY;
				offsetX = (float) (Math.sin(angle) * radius);
				offsetY = (float) (Math.cos(angle) * radius);
				super.translate(offsetX, offsetY);
				radius *= 0.95;
				angle +=(150 + rand.nextInt(60)); 
		
				timer = 0;
				
				if(radius < 2) {
					shaking = false;
					super.position.x = centerX;
					super.position.y = centerY;
				}
			}
		}		
		
	}
}
