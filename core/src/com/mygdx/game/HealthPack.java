package com.mygdx.game;


import com.badlogic.gdx.math.Vector2;

public class HealthPack {
	 private Vector2 position;

	public HealthPack(float x, float y) {
		position = new Vector2(x,y);;
	}
	
	public Vector2 getPosition() {
	    return position;    
	}
}
