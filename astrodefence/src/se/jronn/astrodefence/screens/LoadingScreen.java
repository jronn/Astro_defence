package se.jronn.astrodefence.screens;

import se.jronn.astrodefence.MyGame;
import se.jronn.astrodefence.model.LoadingBar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

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
public class LoadingScreen implements Screen{
	
	MyGame game;
	private Stage stage;
	private Image loadingFrame;
    private Image loadingBg;
    private Image screenBg;
    private Image loadingText;

    private float percent;
    
    private Actor loadingBar;

	public LoadingScreen(MyGame game) {
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		// Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        //Statement returns true when done loading
        if (MyGame.manager.update())    
        	game.setScreen(new MainMenuScreen(game));
           
  
        // Interpolate the percentage to make it more smooth
        percent = Interpolation.linear.apply(percent, MyGame.manager.getProgress(), 0.1f);

        // Update positions (and size) to match the percentage
        loadingBg.setX(loadingBar.getX() + loadingBar.getWidth() * percent);
        loadingBg.setWidth(loadingBar.getWidth() - loadingBar.getWidth() * percent);
        loadingBg.invalidate();

        // Show the loading screen
        stage.act();
        stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		float wUnit = height/10;
		
        stage.setViewport(width , height, false);
        	
        // Make the background fill the screen
        screenBg.setSize(width, height);

        // Place the loading frame in the middle of the screen
        //frame 8:1
        loadingFrame.setSize(10*wUnit, 1.25f*wUnit);
        loadingFrame.setX((stage.getWidth() - loadingFrame.getWidth()) / 2);
        loadingFrame.setY((stage.getHeight() - loadingFrame.getHeight()) / 3);

        // Place the loading bar at the same spot as the frame, adjusted a few px
        loadingBar.setSize(10*wUnit, 1.25f*wUnit);
        loadingBar.setX(loadingFrame.getX());
        loadingBar.setY(loadingFrame.getY());

        // The hidden bar
        loadingBg.setSize(loadingBar.getWidth(), loadingBar.getHeight());
        loadingBg.setX(loadingBar.getX());
        loadingBg.setY(loadingBar.getY());
        
        // The loading text
        loadingText.setSize(8*wUnit, wUnit);
        loadingText.setX((stage.getWidth() - loadingText.getWidth())/2);
        loadingText.setY(loadingBar.getY()+loadingBar.getHeight()*2);
	}

	@Override
	public void show() {
		// Tell the manager to load assets for the loading screen
        MyGame.manager.load("data/loading/loading.pack", TextureAtlas.class);

        // Wait until they are finished loading
        MyGame.manager.finishLoading();

        // Initialize the stage where we will place everything
        stage = new Stage();

        TextureAtlas atlas = MyGame.manager.get("data/loading/loading.pack", TextureAtlas.class);
        
        // Grab the regions from the atlas and create some images
        loadingFrame = new Image(atlas.findRegion("loading-frame"));
        loadingBg = new Image(atlas.findRegion("loading-frame-bg"));
        screenBg = new Image(atlas.findRegion("loading-frame-bg"));
        loadingText = new Image(atlas.findRegion("loading-text"));
        
        Animation anim = new Animation(0.08f, atlas.findRegions("loading-bar"));
        anim.setPlayMode(Animation.LOOP);
        loadingBar = new LoadingBar(anim);
        
        // Add all the actors to the stage
        stage.addActor(screenBg);
        stage.addActor(loadingBar);
        stage.addActor(loadingBg);
        stage.addActor(loadingFrame);
        stage.addActor(loadingText);
   
        // Add everything to be loaded
        MyGame.manager.load("data/game/game.pack", TextureAtlas.class);
        MyGame.manager.load("sound/Explosion.wav", Sound.class);
        MyGame.manager.load("sound/gameOver.wav", Sound.class);
        MyGame.manager.load("sound/Ouroboros.mp3", Music.class);
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		MyGame.manager.unload("data/loading/loading.pack");
		stage.dispose();
	}

}
