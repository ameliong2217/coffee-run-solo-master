package com.cmsc355.coffeerun.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.cmsc355.coffeerun.Sprites.Obstacles;
import com.cmsc355.coffeerun.Sprites.Student;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class GameOverStateTest {



    @Test
    public void PlayAgainTest() {
        Input mockInput = Mockito.mock(Input.class);
        when(mockInput.getX()).thenReturn(50);
        when(mockInput.getY()).thenReturn(50);
        Texture texture = Mockito.mock(Texture.class);
        when(texture.getWidth()).thenReturn(300);
        when(texture.getHeight()).thenReturn(300);
        State state = Mockito.mock(PlayState.class);
        Gdx.graphics = Mockito.mock(Graphics.class);
        when(Gdx.graphics.getWidth()).thenReturn(50);
        when(Gdx.graphics.getWidth()).thenReturn(50);
        when(mockInput.justTouched()).thenReturn(true);

        GameStateManager gsm = new GameStateManager();
        gsm.push(state);
        GameOverState gameState = new GameOverState(gsm, mockInput);
        gameState.doHandleInput(texture, state, false);


        assertEquals(gsm.getCurrentState(), "[" + state.toString() + "]");
    }


    @Test
    public void GameOverOnCollisionTest(){
        Obstacles obstacles = Mockito.mock(Obstacles.class);
        Student student = Mockito.mock(Student.class);
        State gameOverStateMock = Mockito.mock(GameOverState.class);
        GameStateManager gsm = new GameStateManager();

        gsm.push(gameOverStateMock);
        when(obstacles.collides(student.getPlayerBounds())).thenReturn(true);
        assertEquals(gsm.getCurrentState(),"[" + gameOverStateMock.toString() + "]");

    }

    @Test
    public void GameOverOnFatalHealthTest(){

        State state = Mockito.mock(PlayState.class);
        State gameOverStateMock = Mockito.mock(GameOverState.class);
        GameStateManager gsm = new GameStateManager();

        gsm.push(gameOverStateMock);
        when(((PlayState) state).getHealth()).thenReturn(0.0f);
        assertEquals(gsm.getCurrentState(),"[" + gameOverStateMock.toString() + "]");

    }

}

