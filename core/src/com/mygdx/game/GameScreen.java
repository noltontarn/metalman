package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen extends ScreenAdapter{
	private MetalManGame metalmangame;
	private World world;
	private MetalMan metalman;
	private Stage stage;
	private WorldRenderer worldrenderer;
	private Bullet b;
	private long bullettimer;
	private boolean checkdelay = false;
	private long jumpstart;
	private boolean checkjump = false;
	private boolean checkfall = false;
	private ArrayList<Bullet> bullet;
	
	public GameScreen(MetalManGame metalmangame) {
		this.metalmangame = metalmangame;
        world = new World(metalmangame);
        worldrenderer = new WorldRenderer(metalmangame, world);
	}

    @Override
    public void render(float delta) {
    	update(delta);
        worldrenderer.render(delta);
    }
    
    private void update(float delta) {
    	delayBullet();
    	checkJumpTime();
    	checkOnStage();
    	updateMetalMan();
    	checkGameOver();
        world.update(delta);
    }
    
    private void delayBullet() {
    	if(checkdelay == false) {
    		bullettimer = TimeUtils.millis();
    		checkdelay = true;
    	}
    }
    
    private void checkJumpTime() {
    	if(checkjump == true && TimeUtils.millis() - jumpstart > 350) {
    		metalman.isJUMP = 0;
    		metalman.isFALL = 1; 
    	}
    }
    
    private void checkGameOver() {
    	metalman = world.getMetalMan();
    	if(metalman.HP == 0) {
    		metalmangame.setScreen(new GameOverScreen(metalmangame));
    	}
    }
    
    private void checkOnStage() {
    	metalman = world.getMetalMan();
    	stage = world.getStage();
    	if(metalman.getPosition().y == 100 || 
    	(metalman.getPosition().y == 300 && metalman.getPosition().x > -50 && metalman.getPosition().x < 300 && metalman.isFALL == 1) ||
    	(metalman.getPosition().y == 300 && metalman.getPosition().x > 450 && metalman.getPosition().x < 800 && metalman.isFALL == 1) ||
    	(metalman.getPosition().y == 500 && metalman.getPosition().x > 150 && metalman.getPosition().x < 600 && metalman.isFALL == 1)) {
    		stage.isOnStage = true;
    	}
    	else {
    		stage.isOnStage = false;
    		checkjump = true;
    	}
    	if(stage.isOnStage) {
    		checkjump = false;
    		metalman.isFALL = 0; 
    	}
    	if(stage.isOnStage == false && checkjump == false) {
    		metalman.isFALL = 1;
    	}
    }
    
    private void updateMetalMan() {
    	metalman = world.getMetalMan();
    	Vector2 pos = metalman.getPosition();
    	bullet = world.getBullet();
    	stage = world.getStage();
    	if(Gdx.input.isKeyPressed(Keys.SPACE) & TimeUtils.millis() - bullettimer >200) {
    		if(metalman.Face == MetalMan.FACERIGHT) {
    			bullet.add(new Bullet(pos.x + 80, pos.y + 40, metalman.Face));
	        }
    		else if(metalman.Face == MetalMan.FACELEFT){
    			bullet.add(new Bullet(pos.x, pos.y + 40, metalman.Face));
    		}
    		checkdelay = false;
    	}
    	if(Gdx.input.isKeyPressed(Keys.UP)) {
    		if(checkjump == false) {
    			metalman.isJUMP = 1;
    			jumpstart = TimeUtils.millis();
    			checkjump = true;
    			stage.isOnStage = false;
    		}
    	}
    	if(Gdx.input.isKeyPressed(Keys.LEFT)) {
    		metalman.setDirection(MetalMan.DIRECTION_LEFT);
    		metalman.setFace(MetalMan.FACELEFT);
        }
       	else if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
    		metalman.setDirection(MetalMan.DIRECTION_RIGHT);
    		metalman.setFace(MetalMan.FACERIGHT);
    	}
    	else {
    		metalman.setDirection(MetalMan.DIRECTION_STILL);
    	}
    }
}
