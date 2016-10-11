package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen extends ScreenAdapter{
	private MetalManGame metalmangame;
	private World world;
	private MetalMan metalman;
	private WorldRenderer worldrenderer;
	
	public GameScreen(MetalManGame metalmangame) {
		this.metalmangame = metalmangame;
        world = new World(metalmangame);
        worldrenderer = new WorldRenderer(metalmangame, world);
	}

    @Override
    public void render(float delta) {
    	update(delta);
    	
    	Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        worldrenderer.render(delta);
    }
    
    private void update(float delta) {
    	updateMetalMan();
        world.update(delta);
    }
    
    private void updateMetalMan() {
    	metalman = world.getMetalMan();
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
