package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverScreen extends ScreenAdapter{

	private MetalManGame metalmangame;
	private Texture gameoverImg;
	private SpriteBatch batch;
	
	public GameOverScreen(MetalManGame metalmangame) {
		this.metalmangame = metalmangame;
		batch = metalmangame.batch;
		gameoverImg = new Texture("gameover.jpg");
	}
	
	public void render(float delta) {
		update(delta);
		batch.begin();
		batch.draw(gameoverImg, 0, 0);
		batch.end();
	}
	
	public void update(float delta) {
		if(Gdx.input.isKeyPressed(Keys.ENTER)) {
			metalmangame.setScreen(new GameScreen(metalmangame));
		}
	}
} 