package com.cmsc355.coffeerun.States;

import com.badlogic.gdx.Input;
import com.cmsc355.coffeerun.Sprites.Cups;
import com.cmsc355.coffeerun.Sprites.Student;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class CoffeeCupTest {
    @Test
    public void multipleCups() {
        PlayState playState = Mockito.mock(PlayState.class);
        when(playState.getCupsCount()).thenReturn(4);

        assertTrue(playState.getCupsCount() > 1);

    }

    @Test
    public void hitCoffeeCup(){
        Input mockInput = Mockito.mock(Input.class);
        Student student = Mockito.mock(Student.class);
        Cups cup = Mockito.mock(Cups.class);
        State state = Mockito.mock(PlayState.class);
        GameStateManager gsm = new GameStateManager();

        gsm.push(state);

        when(cup.collides(student.getPlayerBounds())).thenReturn(true);
        when(((PlayState) state).getHealth()).thenReturn(1.0f);
        PlayState playState = new PlayState(gsm, mockInput, student);

        playState.doHandleInput(mockInput, student);


        assertTrue(((PlayState) state).getHealth()>0);

    }
    @Test
    public void noCoffeeCupsAndDie(){
        State state = Mockito.mock(PlayState.class);
        State menuStateMock = Mockito.mock(MenuState.class);
        GameStateManager gsm = new GameStateManager();

        gsm.push(menuStateMock);
        when(((PlayState) state).getHealth()).thenReturn(0.0f);
        assertEquals(gsm.getCurrentState(),"[" + menuStateMock.toString() + "]");

    }

}

