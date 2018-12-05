package com.cmsc355.coffeerun.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.cmsc355.coffeerun.CoffeeRun;
import com.cmsc355.coffeerun.Sprites.Student;


public class MusicState extends State {
    private Texture ingameBackground;
    private Texture soundButton;
    private Texture musicButton;
    private Texture backBtn;
    private final Input input;
    private final Vector3 clickposition;
    private boolean soundOff;
    public MusicState(GameStateManager gsm){
        super(gsm);
        ingameBackground = new Texture("background.png");
        soundButton = new Texture("sound.png");
        musicButton = new Texture("music.png");
        backBtn = new Texture("backbutton.png");
        this.input = Gdx.input;
        clickposition = new Vector3();
        soundOff = true;

    }

    public MusicState(GameStateManager gsm, Input input) {
        super(gsm);
        this.input = input;
        clickposition = new Vector3(); //talk about this
    }

    @Override
    protected void handleInput() {
        doHandleInput(musicButton,1, CoffeeRun.music, new MusicState(gsm), false);
        doHandleInput(soundButton, 2, CoffeeRun.music, new MusicState(gsm), false);
        doHandleInput(soundButton,3, CoffeeRun.music, new MenuState(gsm), true);

    }

    public void doHandleInput(Texture texture, int choice, Music music, State state, boolean doDispose) {

        if (input.justTouched()) {
            clickposition.set(input.getX(), input.getY(), 0);
            if (choice == 1) {
                //this is for music button
                if (clickposition.x < Gdx.graphics.getWidth() / 2 && clickposition.x > Gdx.graphics.getWidth() / 2 - 2 * (texture.getWidth() / 10))
                    if (clickposition.y > Gdx.graphics.getHeight() / 2 && clickposition.y < Gdx.graphics.getHeight() / 2 + 2 * (texture.getWidth() / 10)) {
                        if (music.getVolume() != 0)
                            music.setVolume(0);
                        else
                            music.setVolume(.8f);
                    }
            }


            if (choice == 2) {
                //this is for sound button
                if (clickposition.x > Gdx.graphics.getWidth() / 2 && clickposition.x < Gdx.graphics.getWidth() / 2 + 2 * (texture.getWidth() / 10))
                    if (clickposition.y > Gdx.graphics.getHeight() / 2 && clickposition.y < Gdx.graphics.getHeight() / 2 + 2 * (texture.getWidth() / 10)) {
                        if (soundOff) {
                            Student.flap.setVolume(0, 0.0f);

                        }
                    }
            }

            if(choice == 3){
                if(clickposition.x > 0 && clickposition.x < texture.getWidth()) {
                    System.out.println(input.getX());
                    System.out.println(input.getY());
                    if(clickposition.y > 0 && clickposition.y < texture.getHeight()/2)
                    gsm.set(state);
                    if (doDispose) {
                        dispose();
                    }
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
        sb.draw(ingameBackground,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(musicButton,Gdx.graphics.getWidth()/2 - 2*(musicButton.getWidth()/10), Gdx.graphics.getHeight()/2- (musicButton.getHeight()/10), musicButton.getWidth()/10, musicButton.getHeight()/10);
        sb.draw(soundButton, Gdx.graphics.getWidth()/2 + (musicButton.getWidth()/10), Gdx.graphics.getHeight()/2 - musicButton.getHeight()/10,musicButton.getWidth()/10, musicButton.getHeight()/10);
        sb.draw(backBtn, 0,Gdx.graphics.getHeight() - backBtn.getHeight()/2, backBtn.getWidth()/2, backBtn.getHeight()/2);

        sb.end();
    }

    private void dispose() {

    }
}
