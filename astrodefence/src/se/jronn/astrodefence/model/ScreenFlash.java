package se.jronn.astrodefence.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ScreenFlash extends Sprite{
	private float opacity;
	
	public ScreenFlash(TextureRegion r, float o) {
		super(r);
		setOpacity(o);
	}
	
	public void setOpacity(float o) {
		this.opacity = o;
	}
	
	public float getOpacity() {
		return opacity;
	}
}
