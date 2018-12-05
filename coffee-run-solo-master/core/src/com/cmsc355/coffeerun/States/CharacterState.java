package com.cmsc355.coffeerun.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;


public class CharacterState extends State {
    private Texture char1, char2; // the two character assets
    private Texture bg; // background of the character selector screen.
    private final Vector3 clickposition;
    private final Input input;



    public CharacterState(GameStateManager gsm){
        super(gsm);
        this.input = Gdx.input;
        char1 = new Texture("player_temporary_asset.png");
        char2 = new Texture("orange.png");
        bg = new Texture("background.png");
        clickposition = new Vector3();
    }


    public CharacterState(GameStateManager gsm, Input input){
        super(gsm);
        this.input = input;
        clickposition = new Vector3();

    }

    protected void handleInput() {

        doHandleInput(new PlayState(gsm,char1),1,true);
        doHandleInput(new PlayState(gsm,char2),2,true);
    }

    public void doHandleInput(State newState,int choice, boolean doDispose){

        if(input.justTouched()) {
            // Gets clicked/ touched position
            if(choice == 1) {
                clickposition.set(input.getX(), input.getY(), 0); // screen coordinates.
                if (clickposition.x < (Gdx.graphics.getWidth() / 2)) {
                    gsm.set(newState);
                    if (doDispose)
                        dispose();

                }
            }
            else{
                gsm.set(newState);
                if(doDispose)
                    dispose();
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
        sb.draw(bg,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(char1,0,Gdx.graphics.getHeight()/2-char1.getHeight()/2);
        sb.draw(char2, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2-char2.getHeight()/2);
        sb.end();
    }

    private void dispose() {
        bg.dispose();
        char1.dispose();
        char2.dispose();
    }

}
