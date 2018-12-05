package com.cmsc355.coffeerun.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


import java.util.Random;

public class Cups extends Sprite {

    private final Texture coffeeCup;
    private final Vector2 btmPos;
    private final Random rand;
    private static final int FLUCTUATION = 500;
    private final Rectangle bounds;


    public Cups(float x){
        coffeeCup = new Texture("coffeeasset1.png");
        rand = new Random();
        btmPos = new Vector2(x, rand.nextInt(FLUCTUATION));
        bounds = new Rectangle(btmPos.x, btmPos.y, 100, 100);

    }

    public Texture getCoffeeCup() {
        return coffeeCup;
    }

    public Vector2 getBtmPos() {
        return btmPos;
    }

    public void reposition(float x){
        btmPos.set(x, rand.nextInt(FLUCTUATION));
        bounds.setPosition(btmPos.x, btmPos.y);

    }

    public Rectangle getBounds(){
        return bounds;
    }

    public boolean collides(Rectangle student){
        return student.overlaps(bounds);
   }


    public void dispose(){

    }



}
