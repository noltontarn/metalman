package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class FlyEnemy {
	private Vector2 position;
	public int Face = FACERIGHT;
	public static final int FACERIGHT = 1;
	public static final int FACELEFT = 2;
	public static final int SPEED = 6;
	public int HP = 100;
	
	public FlyEnemy(int x, int y, int face) {
		position = new Vector2(x,y);
		setFace(face);
	}
	
    public void setFace(int face) {
        Face = face;
    }
  
    public void getDamage(int damage) {
        HP -= damage;
        if(HP <= 0) {
      	  HP = 0;
        }
        System.out.println(HP);
    }
  
    public Vector2 getPosition() {
        return position;    
    }
  
    public void update() {
	    if(Face == FACERIGHT) {
		    position.x += SPEED;
		    if(getPosition().x > 720) {
			    setFace(FACELEFT);
		    }
	    }
	    else if(Face == FACELEFT) {
		    position.x -= SPEED;
		    if(getPosition().x < 0) {
			    setFace(FACERIGHT);
		    }
	    }
    } 
}
