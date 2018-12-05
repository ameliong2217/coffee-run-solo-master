package com.cmsc355.coffeerun.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import com.badlogic.gdx.math.Rectangle;

public class Student extends Sprite {
    private static final int GRAVITY = -100;
    private Rectangle playerBounds;

    private final Vector3 position;
    private final Vector3 velocity;
    public static Sound flap;
    private boolean sound;
  //  public boolean colliding;



    private int yOriginal = 0; //original yposition of the student
    private Texture student;

    public Student(int x, int y){
        this.yOriginal = y;
        position = new Vector3(0,0,0);
        velocity = new Vector3(x,y,0);
        student = new Texture("player_temporary_asset.png");
        playerBounds = new Rectangle(x, y, 200, 200);
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
        sound = true;
      //  colliding = false;


    }
    public Student(int x, int y, Texture sp){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        student = new Texture(sp.getTextureData());
    }


//    //testing constructor
    public Student(int x, int y, int z) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, y, 0);
    }

    public void update(float dt)
    {

        if(position.y>yOriginal+20) {
                velocity.add(0, GRAVITY, 0);
        }
        else if(position.y != yOriginal) {
            velocity.y = 0;
            position.y =yOriginal;
        }



        velocity.scl(dt);

        position.add(0,velocity.y,0);
        velocity.scl(1/dt);

        playerBounds.setPosition(position.x, position.y);

    }

    public Rectangle getPlayerBounds(){
        return playerBounds;

    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public Texture getStudent(){

        return student;
    }

    public void jump(){
        velocity.y = 3000;
        if(sound)
            flap.play();

}

    public void setSound(boolean soundBool){this.sound = soundBool;}


}
