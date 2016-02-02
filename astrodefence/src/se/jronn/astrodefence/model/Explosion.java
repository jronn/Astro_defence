package se.jronn.astrodefence.model;

import se.jronn.astrodefence.MyGame;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * Represents an explosion
 * 
 * @author Greedy
 * @since 2014-03-07
 * @version 1.0
 */
public class Explosion implements Poolable{
	
	TextureAtlas atlas;
    Animation animation;
    private float x, y, width, height;
    TextureRegion reg;
    float stateTime;

    public Explosion() {
        atlas = MyGame.manager.get("data/game/game.pack", TextureAtlas.class); 
        animation = new Animation(0.04f, atlas.findRegions("explosion"));
        reg = animation.getKeyFrame(0);
    }

    /*
     * Updates state time, and returns true if animation has finished
     */
    public boolean update(float delta) {
    	stateTime += delta;
    	if(animation.isAnimationFinished(stateTime))
    		return true;
    	else
    		return false;
    }
    
    //Initializes the size and position of the explosion
    public void init(float x, float y, float width) {
    	setX(x);
    	setY(y);
    	setSize(width, width);
    }
    
	@Override
	public void reset() {
		stateTime = 0;
	}
    
    public TextureRegion getKeyFrame() {
    	reg = animation.getKeyFrame(stateTime);
    	return reg;
    }
    
    public void setX(float x) {
    	this.x = x;
    }
    
    public void setY(float y) {
    	this.y = y;
    }
    
    public void setSize(float w, float h) {
    	this.width = w;
    	this.height = h;
    }
    
    public float getX() {
    	return x;
    }
    
    public float getY() {
    	return y;
    }
    
    public float getWidth() {
    	return width;
    }
    
    
    public float getHeight() {
    	return height;
    }
    
}
