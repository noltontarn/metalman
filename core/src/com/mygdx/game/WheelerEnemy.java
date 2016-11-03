package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class WheelerEnemy {
	 private Vector2 position;
	 public int Face = FACERIGHT;
	 public static final int FACERIGHT = 1;
	 public static final int FACELEFT = 2;
	 public static final int SPEED = 5;
	 public int HP = 140;
	 private int Start = 0;
	 private int End = 0; 

	public WheelerEnemy(int x, int y, int face, int start, int end) {
		position = new Vector2(x,y);
		setFace(face);
		Start = start;
		End = end;
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
		  if(getPosition().x > End - 50) {
			  setFace(FACELEFT);
		  }
	  }
	  else if(Face == FACELEFT) {
		  position.x -= SPEED;
		  if(getPosition().x < Start - 50) {
			  setFace(FACERIGHT);
		  }
	  }
  }
}
