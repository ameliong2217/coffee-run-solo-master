package com.cmsc355.coffeerun.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.cmsc355.coffeerun.CoffeeRun;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class MusicStateTest extends GameTest {
    private final GameStateManager gsm = new GameStateManager();
    private final Music music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));



    @Test
    public void clickOnSettingsGear_goToMusicMenu(){
        Input mockInput = Mockito.mock(Input.class);
        when(mockInput.getX()).thenReturn(50);
        when(mockInput.getY()).thenReturn(50);
        //SpriteBatch sb = Mockito.mock(SpriteBatch.class);
        Texture texture = Mockito.mock(Texture.class);
        when(texture.getWidth()).thenReturn(300);
        when(texture.getHeight()).thenReturn(300);

        State state = Mockito.mock(MusicState.class);

        Gdx.graphics = Mockito.mock(Graphics.class);
        when(Gdx.graphics.getWidth()).thenReturn(50);
        when(Gdx.graphics.getWidth()).thenReturn(50);
        when(mockInput.justTouched()).thenReturn(true);
        GameStateManager gsm = new GameStateManager();
        gsm.push(state);
        MenuState menuState = new MenuState(gsm, mockInput);
        menuState.doHandleInput(texture,3, state, false);


        assertEquals(gsm.getCurrentState(),"[" + state.toString() + "]");
    }

    @Test
    public void clickOnMusicIcon_OnTheLeft_TurnOffMusic(){
        Input mockInput = Mockito.mock(Input.class);
        when(mockInput.getX()).thenReturn(10);
        when(mockInput.getY()).thenReturn(1);
        Texture texture = Mockito.mock(Texture.class);
        when(texture.getWidth()).thenReturn(300);
        when(texture.getHeight()).thenReturn(300);

        music.setLooping(true);
        Gdx.graphics = Mockito.mock(Graphics.class);
        when(Gdx.graphics.getWidth()).thenReturn(50);
        when(Gdx.graphics.getWidth()).thenReturn(50);
        when(mockInput.justTouched()).thenReturn(true);
        MusicState musicState = new MusicState(gsm, mockInput);

        CoffeeRun.music.setVolume(0.8f);
        musicState.doHandleInput(texture,1, CoffeeRun.music, musicState,false);
        double vol = CoffeeRun.music.getVolume();



        assertEquals((int)vol,0);

    }

    @Test
    public void clickOnTheBackButton_returnToMenuScreen(){
        State state = Mockito.mock(MusicState.class);
        GameStateManager gsm = new GameStateManager();
        gsm.push(state);
        assertEquals(gsm.getCurrentState(),"[" + state.toString() + "]");

    }
}
