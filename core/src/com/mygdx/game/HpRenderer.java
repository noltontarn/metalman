package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HpRenderer {
    private MetalMan metalman;
    private SpriteBatch batch;
    private Texture hp100Img;
    private Texture hp95Img;
    private Texture hp90Img;
    private Texture hp85Img;
    private Texture hp80Img;
    private Texture hp75Img;
    private Texture hp70Img;
    private Texture hp65Img;
    private Texture hp60Img;
    private Texture hp55Img;
    private Texture hp50Img;
    private Texture hp45Img;
    private Texture hp40Img;
    private Texture hp35Img;
    private Texture hp30Img;
    private Texture hp25Img;
    private Texture hp20Img;
    private Texture hp15Img;
    private Texture hp10Img;
    private Texture hp5Img;
    private Texture hp0Img;
    
    public HpRenderer(SpriteBatch batch, MetalMan metalman) {
        this.metalman = metalman;
        this.batch = batch;
        hp100Img = new Texture("hp100.png");
        hp95Img = new Texture("hp95.png");
        hp90Img = new Texture("hp90.png");
        hp85Img = new Texture("hp85.png");
        hp80Img = new Texture("hp80.png");
        hp75Img = new Texture("hp75.png");
        hp70Img = new Texture("hp70.png");
        hp65Img = new Texture("hp65.png");
        hp60Img = new Texture("hp60.png");
        hp55Img = new Texture("hp55.png");
        hp50Img = new Texture("hp50.png");
        hp45Img = new Texture("hp45.png");
        hp40Img = new Texture("hp40.png");
        hp35Img = new Texture("hp35.png");
        hp30Img = new Texture("hp30.png");
        hp25Img = new Texture("hp25.png");
        hp20Img = new Texture("hp20.png");
        hp15Img = new Texture("hp15.png");
        hp10Img = new Texture("hp10.png");
        hp5Img = new Texture("hp5.png");
        hp0Img = new Texture("hp0.png");
    }
    
    public void render() {
    	if(metalman.HP == 100) {
    		batch.draw(hp100Img, 10, 350);
    	}
    	else if(metalman.HP == 95) {
    		batch.draw(hp95Img, 10, 350);
    	}
    	else if(metalman.HP == 90) {
    		batch.draw(hp90Img, 10, 350);
    	}
    	else if(metalman.HP == 85) {
    		batch.draw(hp85Img, 10, 350);
    	}
    	else if(metalman.HP == 80) {
    		batch.draw(hp80Img, 10, 350);
    	}
    	else if(metalman.HP == 75) {
    		batch.draw(hp75Img, 10, 350);
    	}
    	else if(metalman.HP == 70) {
    		batch.draw(hp70Img, 10, 350);
    	}
    	else if(metalman.HP == 65) {
    		batch.draw(hp65Img, 10, 350);
    	}
    	else if(metalman.HP == 60) {
    		batch.draw(hp60Img, 10, 350);
    	}
    	else if(metalman.HP == 55) {
    		batch.draw(hp55Img, 10, 350);
    	}
    	else if(metalman.HP == 50) {
    		batch.draw(hp50Img, 10, 350);
    	}
    	else if(metalman.HP == 45) {
    		batch.draw(hp45Img, 10, 350);
    	}
    	else if(metalman.HP == 40) {
    		batch.draw(hp40Img, 10, 350);
    	}
    	else if(metalman.HP == 35) {
    		batch.draw(hp35Img, 10, 350);
    	}
    	else if(metalman.HP == 30) {
    		batch.draw(hp30Img, 10, 350);
    	}
    	else if(metalman.HP == 25) {
    		batch.draw(hp25Img, 10, 350);
    	}
    	else if(metalman.HP == 20) {
    		batch.draw(hp20Img, 10, 350);
    	}
    	else if(metalman.HP == 15) {
    		batch.draw(hp15Img, 10, 350);
    	}
    	else if(metalman.HP == 10) {
    		batch.draw(hp10Img, 10, 350);
    	}
    	else if(metalman.HP == 5) {
    		batch.draw(hp5Img, 10, 350);
    	}
    	else if(metalman.HP == 0) {
    		batch.draw(hp0Img, 10, 350);
    	}
    }
}
