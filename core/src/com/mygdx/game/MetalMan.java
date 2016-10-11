package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class MetalMan {
	 private Vector2 position;
	 public int Direction;
	 public int Face;
	 public static final int DIRECTION_RIGHT = 1;
	 public static final int DIRECTION_LEFT = 2;
	 public static final int DIRECTION_STILL = 0;
	 public static final int FACERIGHT = 1;
	 public static final int FACELEFT = 2;
	 public static final int SPEED = 5;

	public MetalMan(int x, int y) {
		position = new Vector2(x,y);
        Direction = DIRECTION_STILL;
	}
	
    public void setDirection(int dir) {
        Direction = dir;
    }
    
    public void setFace(int face) {
        Face = face;
    }
    
    public Vector2 getPosition() {
        return position;    
    }
    
    public void update() {
        if(Direction == DIRECTION_STILL) {
        	position.x += 0;
        }
        else if(Direction == DIRECTION_RIGHT) {
        	position.x += SPEED;
        }        
        else if(Direction == DIRECTION_LEFT) {
        	position.x -= SPEED;
        }
    }
}