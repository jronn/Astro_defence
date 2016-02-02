package se.jronn.astrodefence;

import se.jronn.astrodefence.screens.LoadingScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;


/**
 * Startup class
 * 
 * @author Greedy
 * @since 2014-03-06
 * @version 1.0
 */
public class MyGame extends Game {
	
	public static AssetManager manager;
	public ActionResolver actionResolver;
	
	public MyGame(ActionResolver actionResolver) {
		this.actionResolver = actionResolver;
	}
	
	public MyGame() {
	}

	@Override
	public void create() {
		manager = new AssetManager();
		setScreen(new LoadingScreen(this));
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void render() {		
		super.render();
	}
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
