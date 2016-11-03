package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StageRenderer {
    private Stage stage;
    private SpriteBatch batch;
    private Texture stageImg;
 
    public StageRenderer(SpriteBatch batch, Stage stage) {
        this.stage = stage;
        this.batch = batch;
        stageImg = new Texture("stage.png");
    }
 
    public void render() {
        for(int r = 0; r < 6; r++) {
            for(int c = 0; c < 8; c++) {
                int x = c * 100;
                int y = r*100;
 
                if(stage.hasStageAt(r, c)) {
                    batch.draw(stageImg, x, y);
                }
            }
        }
    }
}
