package se.jronn.astrodefence.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import se.jronn.astrodefence.MyGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Contains all objects in the game
 * 
 * @author Greedy
 * @since 2014-03-06
 * @version 1.0
 */
public class GameObjects {
	
	TextureAtlas atlas;
	
	private ArrayList<Meteor> meteors;
	private ArrayList<Explosion> explosions;
	private ArrayList<Sprite> targets;
	private ArrayList<Sprite> crosses;
	private ArrayList<Rocket> rockets;
	private Map<Rocket, Sprite> rocketCrossMap;
	private Map<Sprite, Sprite> crossTargetMap;
	private Earth earth;
	private Image pauseButton;
	private Sprite bombButton;
	private ScreenFlash screenFlash;
	
	public GameObjects() {
		atlas = MyGame.manager.get("data/game/game.pack", TextureAtlas.class);
		
		setCrosses(new ArrayList<Sprite>());
		setCrossTargetMap(new HashMap<Sprite, Sprite>());
		setEarth(new Earth(atlas.findRegion("earth2")));
		setExplosions(new ArrayList<Explosion>());
		setMeteors(new ArrayList<Meteor>());
		setRocketCrossMap(new HashMap<Rocket, Sprite>());
		setRockets(new ArrayList<Rocket>());
		setTargets(new ArrayList<Sprite>());
		
		screenFlash = new ScreenFlash(atlas.findRegion("flash"), 0f);
		screenFlash.setSize(Gdx.graphics.getWidth() * 1.5f, Gdx.graphics.getHeight() * 1.5f);
		screenFlash.setPosition(-Gdx.graphics.getWidth() * 0.25f, -Gdx.graphics.getHeight() * 0.25f);
	}
	
	public void reset() {
		setCrosses(new ArrayList<Sprite>());
		setCrossTargetMap(new HashMap<Sprite, Sprite>());
		setExplosions(new ArrayList<Explosion>());
		setMeteors(new ArrayList<Meteor>());
		setRocketCrossMap(new HashMap<Rocket, Sprite>());
		setRockets(new ArrayList<Rocket>());
		setTargets(new ArrayList<Sprite>());
		getEarth().reset();
		
		screenFlash = new ScreenFlash(atlas.findRegion("flash"), 0f);
		screenFlash.setSize(Gdx.graphics.getWidth() * 1.5f, Gdx.graphics.getHeight() * 1.5f);
		screenFlash.setPosition(-Gdx.graphics.getWidth() * 0.25f, -Gdx.graphics.getHeight() * 0.25f);
	}
	
	public ScreenFlash getScreenFlash() {
		return screenFlash;
	}
	
	public ArrayList<Meteor> getMeteors() {
		return meteors;
	}
	public void setMeteors(ArrayList<Meteor> meteors) {
		this.meteors = meteors;
	}
	public ArrayList<Explosion> getExplosions() {
		return explosions;
	}
	public void setExplosions(ArrayList<Explosion> explosions) {
		this.explosions = explosions;
	}
	public ArrayList<Sprite> getTargets() {
		return targets;
	}
	public void setTargets(ArrayList<Sprite> targets) {
		this.targets = targets;
	}
	public ArrayList<Sprite> getCrosses() {
		return crosses;
	}
	public void setCrosses(ArrayList<Sprite> crosses) {
		this.crosses = crosses;
	}
	public ArrayList<Rocket> getRockets() {
		return rockets;
	}
	public void setRockets(ArrayList<Rocket> rockets) {
		this.rockets = rockets;
	}
	public Map<Rocket, Sprite> getRocketCrossMap() {
		return rocketCrossMap;
	}
	public void setRocketCrossMap(Map<Rocket, Sprite> rocketCrossMap) {
		this.rocketCrossMap = rocketCrossMap;
	}
	public Map<Sprite, Sprite> getCrossTargetMap() {
		return crossTargetMap;
	}
	public void setCrossTargetMap(Map<Sprite, Sprite> crossTargetMap) {
		this.crossTargetMap = crossTargetMap;
	}
	public Earth getEarth() {
		return earth;
	}
	public void setEarth(Earth earth) {
		this.earth = earth;
	}
	
	public void setPauseButton(Image pbtn) {
		this.pauseButton = pbtn;
	}
	
	public Image getPauseButton() {
		return pauseButton;
	}
	
	public void setBombButton(Sprite btn) {
		this.bombButton = btn;
	}
	
	public Sprite getBombButton() {
		return bombButton;
	}
	
}
