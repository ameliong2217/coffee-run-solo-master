package com.cmsc355.coffeerun.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class GameOverState extends State {
    private Texture background;
    private Texture playButton;
    private Texture gameOver;
    private final Vector3 clickposition;
    private final Input input;



    public GameOverState(GameStateManager gsm) {
        super(gsm);
        //this.input = input;
        input = Gdx.input;
        background = new Texture("Menu_NoTitle.png");
        playButton = new Texture("Start_button.png");
        gameOver = new Texture("game_over.jpg");
        clickposition = new Vector3(); //talk about this
    }

    //for testing
    public GameOverState(GameStateManager gsm, Input input) {
        super(gsm);
        this.input = input;
        clickposition = new Vector3(); //talk about this
    }



    @Override
    protected void handleInput() {

        doHandleInput(playButton,new PlayState(gsm), true);

    }

    public void doHandleInput(Texture texture, State newState, boolean doDispose) {
        if (input.justTouched()) {
            // Gets clicked/ touched position

                clickposition.set(input.getX(), input.getY(), 0); // screen coordinates.
                if (clickposition.x > (Gdx.graphics.getWidth() / 2 - (texture.getWidth() / 2)) &&
                        (clickposition.x < (Gdx.graphics.getWidth() / 2 + (texture.getWidth())))) {
                    if (clickposition.y > (Gdx.graphics.getHeight() / 3 - (texture.getHeight() / 3)) &&
                            (clickposition.y < (Gdx.graphics.getHeight() / 3 + (texture.getHeight() / 3)))) {
                       gsm.set(newState);
                        if (doDispose) dispose();
                    }
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(playButton, Gdx.graphics.getWidth() / 2 - (playButton.getWidth() / 2), Gdx.graphics.getHeight() / 3 - (playButton.getHeight() / 3));
        sb.draw(gameOver, Gdx.graphics.getWidth() / 2 -(gameOver.getWidth() / 2), Gdx.graphics.getHeight() - (gameOver.getHeight()));
        sb.end();
    }

    private void dispose() {

        background.dispose();
        playButton.dispose();
    }
}
