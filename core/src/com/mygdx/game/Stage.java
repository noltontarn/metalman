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
}
