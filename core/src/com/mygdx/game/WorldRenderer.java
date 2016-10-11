package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	private MetalManGame metalmangame;
	private SpriteBatch batch;
	private World world;
	private Texture stillImg;
	private Texture stillLeftImg;
	private Texture defaultImg;
	private MetalMan metalman;
	private static final int        FRAME_COLS = 4;         // #1
    private static final int        FRAME_ROWS = 3;         // #2

    Animation                       walkAnimation;          // #3
    Animation                       walkAnimation2;
    Texture                         walkSheet;              // #4
    Texture                         walkSheet2; 
    TextureRegion[]                 walkFrames;             // #5
    TextureRegion[]                 walkFrames2;
    TextureRegion                   currentFrame;           // #7
    TextureRegion                   currentFrame2;  

    float stateTime;                                        // #8
	
	public WorldRenderer(MetalManGame metalmangame, World world) {
        this.metalmangame = metalmangame;
        batch = metalmangame.batch;
        this.world = world;
        metalman = world.getMetalMan();
        stillImg = new Texture("still.png");
        stillLeftImg = new Texture("stillLeft.png");
        defaultImg = new Texture("still.png");
        walkSheet = new Texture("animation1.png"); // #9
        walkSheet2 = new Texture("animation2.png");
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);              // #10
        TextureRegion[][] tmp2 = TextureRegion.split(walkSheet2, walkSheet2.getWidth()/FRAME_COLS, walkSheet2.getHeight()/FRAME_ROWS); 
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        walkFrames2 = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames2[index++] = tmp2[i][j];
            }
        }
        walkAnimation = new Animation(0.045f, walkFrames);      // #11
        walkAnimation2 = new Animation(0.045f, walkFrames2);
        stateTime = 0f;                         // #13
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
    	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);                        // #14
            stateTime += Gdx.graphics.getDeltaTime();           // #15
            currentFrame = walkAnimation.getKeyFrame(stateTime, true);  // #16
            batch.begin();
            batch.draw(currentFrame, pos.x, pos.y);             // #17
            batch.end();
            defaultImg = new Texture("still.png");
    	}
    	else if(metalman.Direction == MetalMan.DIRECTION_LEFT) {
    		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);                        // #14
            stateTime += Gdx.graphics.getDeltaTime();           // #15
            currentFrame = walkAnimation2.getKeyFrame(stateTime, true);  // #16
            batch.begin();
            batch.draw(currentFrame, pos.x, pos.y);             // #17
            batch.end();
            defaultImg = new Texture("stillLeft.png");
    	}
    }
}
