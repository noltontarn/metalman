package com.mygdx.game;

public class World {
	private MetalMan metalman;
	private MetalManGame metalmangame;
	
	World(MetalManGame metalmangame) {
		this.metalmangame = metalmangame;
		metalman = new MetalMan (125,250);
	}
	
	MetalMan getMetalMan() {
		return metalman;
	}
	
	public void update(float delta) {
	       metalman.update();
	}
}
