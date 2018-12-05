package com.cmsc355.coffeerun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.cmsc355.coffeerun.Screens.PlayScreen;
import com.cmsc355.coffeerun.States.GameStateManager;
import com.cmsc355.coffeerun.States.MenuState;

public class CoffeeRun extends Game {

	private GameStateManager gsm;
	public  static Music music;
	//public static boolean musicOn;
    private SpriteBatch batch;

	public static final String TITLE = "Coffee Run";

	public CoffeeRun(){
	}

	public CoffeeRun(Music music){
		CoffeeRun.music = music;
	}


	@Override
	public void create () {
		//musicOn = true;
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(0,0,0,1);
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(.8f);
		//if(musicOn) {
		music.play();
        gsm.push(new MenuState(gsm));
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
		int v_WIDTH = Gdx.graphics.getWidth();
		int v_HEIGHT = Gdx.graphics.getHeight();

	}

	@Override
	public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();

	}
}
