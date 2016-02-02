package se.jronn.astrodefence.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * Loads all needed assets and displays a loading screen while doing so.
 * 
 * Code based on Matsemanns implementation of a loadingscreen in libgdx
 * https://github.com/Matsemann/libgdx-loading-screen
 * 
 * @author Greedy
 * @since 2014-03-06
 * @version 1.0
 */
public class LoadingBar extends Actor {

    Animation animation;
    TextureRegion reg;
    float stateTime;

    public LoadingBar(Animation animation) {
        this.animation = animation;
        reg = animation.getKeyFrame(0);
    }

    @Override
    public void act(float delta) {
        stateTime += delta;
        reg = animation.getKeyFrame(stateTime);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(reg, getX(), getY(), getWidth(), getHeight());
    }
  
}