package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Bullet {
	 private Vector2 position;
	 public int Face = 1;
	 public static final int FACERIGHT = 1;
	 public static final int FACELEFT = 2;
	 public static final int SPEED = 7;
	 
	public Bullet(float x, float y, int face) {
		position = new Vector2(x,y);
		setFace(face);
	}
	
    public Vector2 getPosition() {
        return position;    
    }
	
    public void setFace(int face) {
        Face = face;
    }
    
    public void update() {
		if(Face == FACERIGHT) {
			position.x += SPEED;
		}
		else if(Face == FACELEFT) {
			position.x -= SPEED;
		}
    }
}
