package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class EnemyBullet {
	 private Vector2 position;
	 public int Face = 1;
	 public static final int FACERIGHT = 1;
	 public static final int FACELEFT = 2;
	 public static final int SPEED = 7;
	 public boolean bigbullet = false;
	 
	public EnemyBullet(float x, float y, int face, boolean big) {
		position = new Vector2(x,y);
		bigbullet = big;
		setFace(face);
	}
	
   public Vector2 getPosition() {
       return position;    
   }
	
   public void setFace(int face) {
       Face = face;
   }
   
   public void update() {
		if(Face == FACERIGHT && !bigbullet) {
			position.x += SPEED;
		}
		else if(Face == FACELEFT &&!bigbullet) {
			position.x -= SPEED;
		}
		else if(Face == FACERIGHT && bigbullet) {
			position.x += 4;
		}
		else if(Face == FACELEFT && bigbullet) {
			position.x -= 4;
		}
   }
}
