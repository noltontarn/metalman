package com.mygdx.game;

public class Stage {
	public int xstart;
	public int xend;
	public int y;
	public boolean isOnStage = true;
	private boolean start = false;
	private int floorblock = 0;
	private int floor=0;
	private World world;
	private MetalMan metalman;
	
	private String[] MAP = new String [] {
			"########",
			"........",
			"###..###",
			"........",
			"..####..",
			"........"
	};
	
    public boolean hasStageAt(int r, int c) {
        return MAP[r].charAt(c) == '#';
    } 
    
    /*public void checkStage(World world, MetalMan metalman) {
    	this.world = world;
    	metalman = world.getMetalMan();
    	for(int r = 0;r < 6;r++) {
    		if(isOnStage = true) {
    			break;
    		}
    		for(int c = 0; c < 8; c++) {
    			if(MAP[r].charAt(c) == '#' && start == false) {
    				xstart = c*100;
    				System.out.println(xstart);
    				y = r*100+100;
    				System.out.println(y);
    				start = true;
    			}
    			if((MAP[r].charAt(c) == '.' && start == true) || (c == 7 && start == true && MAP[r].charAt(c) == '#')) {
    				if(c == 7) {
    					xend = 800;
    				}
    				else {
    					xend = c*100;
    				}
    				System.out.println(xend);
    				floorblock++;
    				start = false;
    				if(metalman.getPosition().x > xstart && metalman.getPosition().x < xend && metalman.getPosition().y == y) {
    					isOnStage = true;
    					break;
    				}
    			}
    		}
    		floor++;
    	}
    }*/
}
