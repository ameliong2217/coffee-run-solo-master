package com.cmsc355.coffeerun.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Obstacles extends Sprite {
    private final Texture btmObstacle;
    private final Vector2 btmPos;
    private final Random rand;

    public Rectangle getBounds() {
        return bounds;
    }

    private final Rectangle bounds;




    private static final int FLUCTUATION = 500;

    public Obstacles(float x){
        btmObstacle = new Texture("obstaclebike.png");
        rand = new Random();
        btmPos = new Vector2(x, rand.nextInt(FLUCTUATION));
        bounds = new Rectangle(btmPos.x-=20,btmPos.y,200,100);


    }

    public boolean collides(Rectangle player){

        return player.overlaps(getBounds());
    }
    public Texture getBtmObstacle() {
        return btmObstacle;
    }

    public Vector2 getBtmPos() {
        return btmPos;
    }

    public void reposition(float x){
        btmPos.set(x, rand.nextInt(FLUCTUATION));
        bounds.setPosition(btmPos.x-=20,btmPos.y);

    }



}

