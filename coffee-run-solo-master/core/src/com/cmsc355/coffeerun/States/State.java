package com.cmsc355.coffeerun.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

abstract class State {
    final OrthographicCamera cam;
    final GameStateManager gsm;

    State(GameStateManager gsm){
        this.gsm = gsm;

        cam = new OrthographicCamera();

        //Vector3 mouse = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);

}
