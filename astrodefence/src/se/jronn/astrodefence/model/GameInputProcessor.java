package se.jronn.astrodefence.model;

import se.jronn.astrodefence.MyGame;
import se.jronn.astrodefence.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;


/**
 * Handles user input
 * 
 * @author Greedy
 * @since 2014-03-08
 * @version 1.0
 */
public class GameInputProcessor implements InputProcessor{

	private GameObjects go;
	private Sprite target;
	private Rectangle rectPause;
	private Rectangle rectBomb;
	private TextureAtlas atlas;
 	private float scale;
 	private float rotation;
 	private Rocket rocket;
 	private Sprite cross;
 	public static Boolean pausedStatus;
 	private GameTick tick;
	
	public GameInputProcessor(GameObjects go, GameTick tick) {
		this.go = go;
		atlas = MyGame.manager.get("data/game/game.pack", TextureAtlas.class);
		scale = 1;
		pausedStatus = false;
		rectPause = new Rectangle();
		rectBomb = new Rectangle();
		this.tick = tick;
	}
	

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		rectPause.width = go.getPauseButton().getWidth();
		rectPause.height = go.getPauseButton().getHeight();
		rectPause.x = go.getPauseButton().getX();
		rectPause.y = go.getPauseButton().getY();
		
		rectBomb.width = go.getBombButton().getWidth();
		rectBomb.height = go.getBombButton().getHeight();
		rectBomb.x = go.getBombButton().getX();
		rectBomb.y = go.getBombButton().getY();
		
		//If pause button is pressed
		if(rectPause.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
			pausedStatus = true;
			Gdx.app.log("INPUT", "paused");
			GameScreen.music.pause();
		}
		//If bomb button is pressed
		else if(rectBomb.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
			Gdx.app.log("INPUT", "bomb triggered");
			if(go.getEarth().bombPowerFull()) {
				go.getEarth().clearBombPower();
				tick.clearField = true;
			}
		}
		else if(go.getEarth().getAmmo() >= 1 && target == null) {
			
			//Remove ammo
			go.getEarth().addAmmo(-1);
			
			target = new Sprite(atlas.findRegion("target"));
			target.setSize(Gdx.graphics.getHeight()/10, Gdx.graphics.getHeight()/10);
			target.setPosition(screenX - target.getWidth()/2, Gdx.graphics.getHeight() - screenY - target.getHeight()/2);
        	target.setOrigin(target.getWidth()/2, target.getHeight()/2);
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(target != null) {
			addTarget();
			target = null;
			scale = 1;
			rotation = 0;
		}
		return false;
	}

	public Sprite getTarget(float delta) {
		if(scale < 3)
			scale += 2f * delta;
		else
			scale = 3;
		rotation += 30 * delta;
		target.setScale(scale);
		target.setRotation(rotation);
		return target;
	}
	
	public boolean currentTarget() {
		if(target != null)
			return true;
		else 
			return false;
	}
	
	private void addTarget() {
		
		//Add target to list
		go.getTargets().add(target);
		
		//Add new cross to list
		cross = new Sprite(atlas.findRegion("cross"));
		cross.setSize(Gdx.graphics.getHeight()/20, Gdx.graphics.getHeight()/20);
		cross.setOrigin(cross.getWidth()/2, cross.getHeight()/2);
		cross.setRotation(45);
		cross.setPosition(target.getX()+target.getWidth()/2 - cross.getWidth()/2, target.getY()+target.getHeight()/2 - cross.getHeight()/2);
		go.getCrosses().add(cross);
			
		//Map cross to target
		go.getCrossTargetMap().put(cross, target);
		
		//Map rocket to cross
		rocket = new Rocket(atlas.findRegion("rocket"), target, go.getEarth().getX() + go.getEarth().getWidth()/2, go.getEarth().getY() + go.getEarth().getHeight()/2, Gdx.graphics.getHeight()/45, Gdx.graphics.getHeight()/15);
		go.getRocketCrossMap().put(rocket, cross);
		
		//Add rocket to list
		go.getRockets().add(rocket);
		
		//set target to null, so that it can be initiated again by a touch down
		target = null;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
