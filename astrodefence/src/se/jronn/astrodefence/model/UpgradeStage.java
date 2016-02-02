package se.jronn.astrodefence.model;

import se.jronn.astrodefence.MyGame;
import se.jronn.astrodefence.screens.MainMenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class UpgradeStage extends Stage{
	
	private float width, height;
	private TextureAtlas atlas;
	private FreeTypeFontGenerator fontgen;
	private FreeTypeFontGenerator.FreeTypeFontParameter fontParam;
	private BitmapFont fontSmall;
	private Image upgradeTitle;
	private Label label_tokens;
	private Image backButton;
	private MainMenuScreen menu;
	private Preferences prefs;
	public Sprite[][] upgradeLevel;
	private Image[] upgradeButton;
	
	public UpgradeStage(MainMenuScreen menu, float width, float height) {
		super(width, height);
		this.width = width;
		this.height = height;
		this.menu = menu;
		atlas = MyGame.manager.get("data/game/game.pack", TextureAtlas.class);
		prefs = Gdx.app.getPreferences("se.jronn.astrodefence");
		upgradeLevel = new Sprite[3][5];
		upgradeButton = new Image[3];
		setUpStage();
	}
	
	private void setUpStage() {
		
		String upgrade;
		
		fontParam = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontgen = new FreeTypeFontGenerator(Gdx.files.internal("fonts/visitor1.ttf"));
		
		fontParam.size = (int)(height/10);
		fontSmall = fontgen.generateFont(fontParam);
		
		//Upgrade label
		upgradeTitle = new Image(atlas.findRegion("upgrades"));
		upgradeTitle.setSize(height/1.5f, height/7.5f);
		upgradeTitle.setPosition(width/2 - upgradeTitle.getWidth()/2, height - upgradeTitle.getHeight() * 1.2f);
		
		for(int i = 0; i < 3; i++) {
			Image upgradeBg1, upgradeType1;
			
			switch(i) {
				case 0: upgrade = "shield";
					break;
				case 1: upgrade = "missile";
					break;
				case 2: upgrade = "bomb";
					break;
				default: upgrade = "shield";
					break;
			}
			
			//BG box
			upgradeBg1 = new Image(atlas.findRegion("pauseBg"));
			upgradeBg1.setSize(height, height/5);
			upgradeBg1.setPosition(width/2 - upgradeBg1.getWidth()/2, height - upgradeBg1.getHeight() * 2f - upgradeBg1.getHeight() * 1.1f * i);
				
			//Upgrade type icon
			upgradeType1 = new Image(atlas.findRegion(upgrade + "Upgrade"));
			upgradeType1.setSize(upgradeBg1.getHeight() * 0.8f, upgradeBg1.getHeight() * 0.8f);
			upgradeType1.setPosition(upgradeBg1.getX() + upgradeBg1.getHeight() * 0.1f, upgradeBg1.getY() + upgradeBg1.getHeight() * 0.1f);
		
			//Button box 1 
			upgradeButton[i] = new Image(atlas.findRegion("btnAdd"));
			upgradeButton[i].setSize(upgradeBg1.getHeight() * 0.8f, upgradeBg1.getHeight() * 0.8f);
			upgradeButton[i].setPosition(upgradeBg1.getX() + upgradeBg1.getWidth() - upgradeButton[i].getWidth() - upgradeBg1.getHeight() * 0.1f, upgradeBg1.getY() + upgradeBg1.getHeight() * 0.1f);
		
			//Upgrade level boxes
			for(int j = 0; j < upgradeLevel[i].length; j++) {
				
				if(prefs.getInteger(upgrade) >= (j + 1))
					upgradeLevel[i][j] = new Sprite(atlas.findRegion("boxMedium"));
				else 
					upgradeLevel[i][j] = new Sprite(atlas.findRegion("pauseBg"));
				
				upgradeLevel[i][j].setSize((upgradeBg1.getWidth() - upgradeButton[i].getWidth() - upgradeType1.getWidth() - upgradeBg1.getHeight() * 0.3f) / 5 - upgradeBg1.getHeight() * 0.1f, upgradeBg1.getHeight() * 0.6f);
				upgradeLevel[i][j].setPosition(upgradeType1.getX() + upgradeType1.getWidth() + upgradeBg1.getHeight() * 0.1f + upgradeLevel[i][j].getWidth() * j + upgradeBg1.getHeight()*0.1f * j, upgradeBg1.getY() + upgradeBg1.getHeight() * 0.2f);
			}
			
			this.addActor(upgradeBg1);
			this.addActor(upgradeType1);
			
			this.addActor(upgradeButton[i]);
		}
		
		upgradeButton[0].addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				int lvl = prefs.getInteger("shield");
				int token = prefs.getInteger("upgradeToken");
				if(lvl <= 4 && token > 0) {
					prefs.putInteger("shield", lvl + 1);
					prefs.putInteger("upgradeToken", token - 1);
					prefs.flush();
					updateUpgradeLevels("shield");
				}
				return true;
			}
		});
		
		upgradeButton[1].addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				int lvl = prefs.getInteger("missile");
				int token = prefs.getInteger("upgradeToken");
				if(lvl <= 4 && token > 0) {
					prefs.putInteger("missile", lvl + 1);
					prefs.putInteger("upgradeToken", token - 1);
					prefs.flush();
					updateUpgradeLevels("missile");
				}
				return true;
			}
		});
		
		upgradeButton[2].addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				int lvl = prefs.getInteger("bomb");
				int token = prefs.getInteger("upgradeToken");
				if(lvl <= 4 && token > 0) {
					prefs.putInteger("bomb", lvl + 1);
					prefs.putInteger("upgradeToken", token - 1);
					prefs.flush();
					updateUpgradeLevels("bomb");
				}
				return true;
			}
		});
		
		
		//Token label
		label_tokens = new Label("TOKENS: " + prefs.getInteger("upgradeToken"), new Label.LabelStyle(fontSmall, new Color(0.8f, 0.6f, 0.22f, 1)));//(0.8f, 0.6f, 0.22f, 1)));
		label_tokens.setPosition(width/2 - label_tokens.getWidth()/2, label_tokens.getHeight() * 0.2f);
		
		//Back button
		backButton = new Image(atlas.findRegion("btnBack"));
		backButton.setSize(height/7, height/7);
		backButton.setPosition(backButton.getWidth() * 0.15f, backButton.getHeight() * 0.15f);
		backButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				menu.upgradeScreen = false; 
				return true;
			}
		});
		
		this.addActor(upgradeTitle);
		//this.addActor(tokenBgPanel);
		this.addActor(label_tokens);
		this.addActor(backButton);
	}
	
	//Updates the upgrade level boxes and the token label
	public void updateUpgradeLevels(String upgrade) {
		int i = 0;
		
		if(upgrade == "shield")
			i = 0;
		else if(upgrade == "missile")
			i = 1;
		else if(upgrade == "bomb")
			i = 2;
		
		for(int j = 0; j < upgradeLevel[i].length; j++) {
			if(prefs.getInteger(upgrade) >= (j + 1))
				upgradeLevel[i][j].setRegion(atlas.findRegion("boxMedium"));
			else 
				upgradeLevel[i][j].setRegion(atlas.findRegion("pauseBg"));
		}
		
		//Update token label as well
		label_tokens.setText("TOKENS: " + Integer.toString(prefs.getInteger("upgradeToken")));
		
	}
}
