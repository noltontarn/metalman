package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	private MetalManGame metalmangame;
	private StageRenderer stageRenderer;
	private HpRenderer hpRenderer;
	private SpriteBatch batch;
	private World world;
	private Texture stillImg;
	private Texture stillLeftImg;
	private Texture defaultImg;
	private Texture jumpImg;
	private Texture jumpLeftImg;
	private Texture background;
	private Texture healthpackImg;
	private Texture bull;
	private Texture ebull;
	private Texture bigebull;
	private Texture bigebullLeft;
	private Texture shooterImg;
	private Texture shooterLeftImg;
	private Texture wheelerImg;
	private Texture cannonImg;
	private Texture cannonLeftImg;
	private MetalMan metalman;
	private ArrayList<Bullet> bullet;
	private ArrayList<EnemyBullet> enemybullet;
	private ArrayList<ShooterEnemy> shooterenemy;
	private ArrayList<WheelerEnemy> wheelerenemy;
	private ArrayList<Cannon> cannon;
	private ArrayList<HealthPack> healthpack;
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
        stageRenderer = new StageRenderer(metalmangame.batch, world.getStage());
        metalman = world.getMetalMan();
        hpRenderer = new HpRenderer(metalmangame.batch, metalman);
        healthpack = world.getHealthPack();
		bullet = world.getBullet();
		shooterenemy = world.getShooterEnemy();
		wheelerenemy = world.getWheelerEnemy();
		cannon = world.getCannon();
		enemybullet = world.getEnemyBullet();
        stillImg = new Texture("still.png");
        stillLeftImg = new Texture("stillLeft.png");
        defaultImg = new Texture("still.png");
        jumpImg = new Texture("jump.png");
        jumpLeftImg = new Texture("jumpLeft.png");
        healthpackImg = new Texture("healthpack.png");
        bull = new Texture("bullet.png");
        ebull = new Texture("ebull.png");
        bigebull = new Texture("bigebull.png");
        bigebullLeft = new Texture("bigebullLeft.png");
        shooterImg = new Texture("shooter.png");
        shooterLeftImg = new Texture("shooterLeft.png");
        wheelerImg = new Texture("wheeler.png");
        cannonImg = new Texture("cannon.png");
        cannonLeftImg = new Texture("cannonLeft.png");
        background = new Texture("background.png");
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
    		batch.draw(background, 0, 0);
            stageRenderer.render();
    		if(metalman.isJUMP == 1 || metalman.isFALL == 1) {
    			if(metalman.Face == MetalMan.FACERIGHT) {
    				batch.draw(jumpImg, pos.x, pos.y); 
    			}
    			else if(metalman.Face == MetalMan.FACELEFT) {
    				batch.draw(jumpLeftImg, pos.x, pos.y);
    			} 
    		}
    		else {
    			batch.draw(defaultImg, pos.x, pos.y); 
    			if(metalman.Face == MetalMan.FACERIGHT) {
    				batch.draw(stillImg, pos.x, pos.y);	
    			}
    			else if(metalman.Face == MetalMan.FACELEFT) {
    				batch.draw(stillLeftImg, pos.x, pos.y);
    			} 	
    		}
            hpRenderer.render();
    		batch.end();  
    	}
    	else if(metalman.Direction == MetalMan.DIRECTION_RIGHT) {
    	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);                        // #14
            stateTime += Gdx.graphics.getDeltaTime();           // #15
            currentFrame = walkAnimation.getKeyFrame(stateTime, true);  // #16
            batch.begin();
            batch.draw(background, 0, 0);
            stageRenderer.render();
    		if(metalman.isJUMP == 1 || metalman.isFALL == 1) {
    			if(metalman.Face == MetalMan.FACERIGHT) {
    				batch.draw(jumpImg, pos.x, pos.y); 
    			}
    			else if(metalman.Face == MetalMan.FACELEFT) {
    				batch.draw(jumpLeftImg, pos.x, pos.y);
    			}  
    		}
    		else {
    			batch.draw(currentFrame, pos.x, pos.y);   // #17
    		}
            hpRenderer.render();
            batch.end();
            defaultImg = new Texture("still.png");
    	}
    	else if(metalman.Direction == MetalMan.DIRECTION_LEFT) {
    		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);                        // #14
            stateTime += Gdx.graphics.getDeltaTime();           // #15
            currentFrame = walkAnimation2.getKeyFrame(stateTime, true);  // #16
            batch.begin(); 
            batch.draw(background, 0, 0);
            stageRenderer.render();
    		if(metalman.isJUMP == 1 || metalman.isFALL == 1) {
    			if(metalman.Face == MetalMan.FACERIGHT) {
    				batch.draw(jumpImg, pos.x, pos.y); 
    			}
    			else if(metalman.Face == MetalMan.FACELEFT) {
    				batch.draw(jumpLeftImg, pos.x, pos.y);
    			} 
    		}
    		else {
    			batch.draw(currentFrame, pos.x, pos.y);   // #17
    		}
            hpRenderer.render();
            batch.end();
            defaultImg = new Texture("stillLeft.png");
    	}
        for(Bullet b : bullet) {
        	batch.begin();
	        Vector2 posb =b.getPosition();
	        batch.draw(bull, posb.x, posb.y);
	        batch.end();
        }
        for(ShooterEnemy s : shooterenemy) {
        	batch.begin();
	        Vector2 poss =s.getPosition();
	        if(s.Face == ShooterEnemy.FACERIGHT) {
	        	batch.draw(shooterImg, poss.x, poss.y);
	        }
	        else if(s.Face == ShooterEnemy.FACELEFT) {
	        	batch.draw(shooterLeftImg, poss.x, poss.y);
	        }
	        batch.end();
        }
        for(WheelerEnemy w : wheelerenemy) {
        	batch.begin();
	        Vector2 posw =w.getPosition();
	        batch.draw(wheelerImg, posw.x, posw.y);
	        batch.end();
        }
        
        for(Cannon c : cannon) {
        	batch.begin();
	        Vector2 posc =c.getPosition();
	        if(c.Face == Cannon.FACERIGHT) {
	        	batch.draw(cannonImg, posc.x, posc.y);
	        }
	        else if(c.Face == Cannon.FACELEFT) {
	        	batch.draw(cannonLeftImg, posc.x, posc.y);
	        }
	        batch.end();
        }
        
        for(EnemyBullet b : enemybullet) {
        	batch.begin();
	        Vector2 posb =b.getPosition();
	        if(b.bigbullet) {
	        	if(b.Face == EnemyBullet.FACERIGHT) {
	        		batch.draw(bigebull, posb.x, posb.y);
	        	}
	        	else if(b.Face == EnemyBullet.FACELEFT) {
	        		batch.draw(bigebullLeft, posb.x, posb.y);
	        	}
	        }
	        else {
	        	batch.draw(ebull, posb.x, posb.y);
	        }
	        batch.end();
        }
        
        for(HealthPack h : healthpack) {
        	batch.begin();
	        Vector2 posh =h.getPosition();
	        batch.draw(healthpackImg, posh.x, posh.y);
	        batch.end();
        }
    }
}