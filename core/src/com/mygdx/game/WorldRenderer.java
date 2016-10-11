package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	private MetalManGame metalmangame;
	private SpriteBatch batch;
	private World world;
	private Texture stillImg;
	private Texture stillLeftImg;
	private Texture defaultImg;
	private MetalMan metalman;
	
	public WorldRenderer(MetalManGame metalmangame, World world) {
        this.metalmangame = metalmangame;
        batch = metalmangame.batch;
        this.world = world;
        metalman = world.getMetalMan();
        stillImg = new Texture("still.png");
        stillLeftImg = new Texture("stillLeft.png");
        defaultImg = new Texture("still.png");
    }
	
	public void render(float delta) {
    	SpriteBatch batch = metalmangame.batch;
    	Vector2 pos = metalman.getPosition();
    	if(metalman.Direction == MetalMan.DIRECTION_STILL) {
    		batch.begin();
    		batch.draw(defaultImg, pos.x, pos.y); 
    		if(metalman.Face == MetalMan.FACERIGHT) {
                batch.draw(stillImg, pos.x, pos.y);
    		}	
    		else if(metalman.Face == MetalMan.FACELEFT) {
                batch.draw(stillLeftImg, pos.x, pos.y);   	
    		}
    		batch.end();  
    	}
    	else if(metalman.Direction == MetalMan.DIRECTION_RIGHT) {
    		batch.begin();
            batch.draw(stillImg, pos.x, pos.y);
            batch.end();  
            defaultImg = new Texture("still.png");
    	}
    	else if(metalman.Direction == MetalMan.DIRECTION_LEFT) {
    		batch.begin();
            batch.draw(stillLeftImg, pos.x, pos.y);
            batch.end();  
            defaultImg = new Texture("stillLeft.png");
    	}
    }
}
