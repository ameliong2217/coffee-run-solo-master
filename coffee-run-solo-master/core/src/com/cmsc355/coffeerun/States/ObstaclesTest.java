package com.cmsc355.coffeerun.States;
import com.cmsc355.coffeerun.Sprites.Obstacles;
import com.cmsc355.coffeerun.Sprites.Student;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ObstaclesTest {


    @Test
    public void multipleObstacleOnScreen() {
        PlayState playState = Mockito.mock(PlayState.class);
        when(playState.getObstacleCount()).thenReturn(4);
        assertTrue(playState.getObstacleCount() > 1);


    }

    @Test
    public void passObstacle(){
        Obstacles obstacles = Mockito.mock(Obstacles.class);
        Student student = Mockito.mock(Student.class);
        State state = Mockito.mock(PlayState.class);
        GameStateManager gsm = new GameStateManager();
        gsm.push(state);

        when(obstacles.collides(student.getPlayerBounds())).thenReturn(false);
        assertEquals(gsm.getCurrentState(),"[" + state.toString() + "]");


    }

    @Test
    public void hitObstacleAndDie(){
        Obstacles obstacles = Mockito.mock(Obstacles.class);
        Student student = Mockito.mock(Student.class);
        State state = Mockito.mock(PlayState.class);
        State menuStateMock = Mockito.mock(MenuState.class);
        GameStateManager gsm = new GameStateManager();

        gsm.push(menuStateMock);
        when(obstacles.collides(student.getPlayerBounds())).thenReturn(true);
        when(((PlayState) state).getHealth()).thenReturn(0.0f);
        assertEquals(gsm.getCurrentState(),"[" + menuStateMock.toString() + "]");

    }

}
