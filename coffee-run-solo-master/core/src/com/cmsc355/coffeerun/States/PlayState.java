package com.cmsc355.coffeerun.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cmsc355.coffeerun.Sprites.Cups;
import com.cmsc355.coffeerun.Sprites.Obstacles;
import com.cmsc355.coffeerun.Sprites.Student;

import java.util.ArrayList;

import static com.badlogic.gdx.Gdx.graphics;

public class PlayState extends State {

    private static final int OBSTACLE_SPACE = 700;
    private static final int OBSTACLE_COUNT = 4;
    private ArrayList<Obstacles> obstacles;
    private final Student student;
    private Obstacles obstacle;
    private float health = 1; //0 = dead, 1 = full health
    private float timeCount = 0;
    private Texture healthBar;
    private final Input input;

    private static final int COFFEE_SPACE = 500;
    private static final int COFFEE_COUNT = 4;
    private ArrayList<Cups> cups;
    private Cups cup;
    private ShapeRenderer shapeRenderer;

    private Texture treeBackground;
    private Texture treeBackgroundTwo;
    private Texture ingameBackground;  // our actual ingame background
    private Sprite backgroundSprite;   // we'll use sprite to wrap our background infinitely
    private float scrollTime = 0;              // the rate in which the background moves



    PlayState(GameStateManager gsm) {
        super(gsm);
        shapeRenderer = new ShapeRenderer();
        student = new Student(graphics.getWidth()/10,graphics.getHeight()/10); //recongigure this for every screen (screemheight/8)
        cam.setToOrtho(false, graphics.getWidth()/5, graphics.getHeight());
        obstacles = new ArrayList<Obstacles>();



            for (int i = 1; i < OBSTACLE_COUNT; i++) {
                obstacles.add(new Obstacles(i * OBSTACLE_SPACE + 52));
            }

        cups = new ArrayList<Cups>();
        for(int i = 1;i<COFFEE_COUNT;i++){
            cups.add(new Cups(i * COFFEE_SPACE + 40));
        }

        ingameBackground = new Texture("background.png");

        // this allows us to use an image to represent our healthbar
        healthBar = new Texture("plain-white-background.jpg");

        cam.setToOrtho(false, graphics.getWidth(),graphics.getHeight());

        // setWrap wraps our background and backgroundSprite actually sets it as our moving background
        ingameBackground.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.ClampToEdge);
        //why the is the image being weird
        backgroundSprite = new Sprite(ingameBackground, 0,-600, graphics.getWidth() , graphics.getHeight());
        input = Gdx.input;



    }
    PlayState(GameStateManager gsm, Texture selectedchar){
        super(gsm);
        student = new Student(200,0, selectedchar);
        obstacles = new ArrayList<Obstacles>();
        for(int i = 1;i<OBSTACLE_COUNT;i++){
            obstacles.add(new Obstacles(i * OBSTACLE_SPACE + 52));
        }
        cups = new ArrayList<Cups>();
        for(int i = 1;i<COFFEE_COUNT;i++){
            cups.add(new Cups(i * COFFEE_SPACE + 40));
        }

        ingameBackground = new Texture("background.png");

        // this allows us to use an image to represent our healthbar
        healthBar = new Texture("plain-white-background.jpg");


        // setWrap wraps our background and backgroundSprite actually sets it as our moving background
        ingameBackground.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.ClampToEdge);
        backgroundSprite = new Sprite(ingameBackground, 0,-600, graphics.getWidth() , graphics.getHeight());
        input = Gdx.input;
    }

    //testing constructor
    public PlayState(GameStateManager gsm, Input input, Student student){
        super(gsm);
        this.student = student;
        this.input = input;
    }
    @Override
    protected void handleInput() {


        doHandleInput(input, student);

    }

    public void doHandleInput(Input input, Student student){
        if(input.justTouched()){
            student.jump();
        }
    }



  

    @Override
    public void update(float dt) {
        handleInput();
        student.update(dt);




        decrementHealth(Gdx.graphics);


        timeCount += dt;


        for(Obstacles obstacle : obstacles){
            if(cam.position.x - (cam.viewportWidth/2)> obstacle.getBtmPos().x + obstacle.getBtmObstacle().getWidth()){
                obstacle.reposition(obstacle.getBtmPos().x +  ((52+OBSTACLE_SPACE) *  OBSTACLE_COUNT));
            }
        }

        for(Cups cup : cups) {
                if (cam.position.x - (cam.viewportWidth / 2) > cup.getBtmPos().x + cup.getCoffeeCup().getWidth()) {
                    cup.reposition(cup.getBtmPos().x + ((40 + COFFEE_SPACE) * COFFEE_COUNT));
                }

    }
        cam.update();

        }

    public void decrementHealth(Graphics graphics){
        if(health*(graphics.getWidth() -100)>0)
            this.health-=.0004f;
    }

    @Override
    public void render(SpriteBatch sb) {


        sb.setProjectionMatrix(cam.combined);
        /* the following 3 lines of code below controls the rate at which the background moves and if it reaches
            a certain threshold then it resets
        */
        scrollTime += 0.01f;
        if(scrollTime >=1.0f) {
            scrollTime = 0.0f;
        }

        sb.begin();
        /* setU determines the starting scroll time which is zero, and SetU2 keeps incrementing that by 10
            sb.draw finally draws the background
        */

        backgroundSprite.setU(scrollTime);
        backgroundSprite.setU2(scrollTime+10);
        sb.draw(backgroundSprite,0,0);

        for(Obstacles obstacle : obstacles){
            sb.draw(obstacle.getBtmObstacle(), obstacle.getBtmPos().x-=10, obstacle.getBtmPos().y);
            if(obstacle.collides(student.getPlayerBounds())) {

                gsm.set(new GameOverState(gsm));
            }


            cam.update();


        }

       for(Cups cup : cups) {
            int counter = 0;

            if(!obstacles.get(counter).collides(cup.getBounds())) {
                sb.draw(cup.getCoffeeCup(), cup.getBtmPos().x -= 10, cup.getBtmPos().y);
            }
            if(cup.collides(student.getPlayerBounds())) {
                if(health<1) {
                    health += .01f;
                }
                sb.draw(cup.getCoffeeCup(), cup.getBtmPos().x, cup.getBtmPos().y-=1000);
            }




        }


        if(health < .001f) {

            gsm.set(new GameOverState(gsm));
        }

        cam.update();
        sb.draw(healthBar,graphics.getWidth()-(graphics.getWidth()/4),graphics.getHeight()-100,(graphics.getWidth()/3-(graphics.getWidth()/8))* health, 60);

        //Gdx.getGraphic. ->can get height and width of any emulator that we use
        sb.draw(student.getStudent(), student.getPosition().x, student.getPosition().y, (graphics.getWidth()/10),(graphics.getWidth()/10));


        sb.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(67 ,   34 , 167, 0.5f);
        shapeRenderer.rect(student.getPlayerBounds().x,student.getPlayerBounds().y,200,120);
        for(Obstacles obstacles: obstacles){
            shapeRenderer.rect(obstacles.getBounds().x-=10,obstacles.getBounds().y,200,120);
        }
        for(Cups cup: cups) {
            shapeRenderer.rect(cup.getBounds().x -= 10, cup.getBounds().y, 75, 100);
            }
            shapeRenderer.end();


    }


    public float getHealth(){
        return health;
    }

    public int getObstacleCount(){
        return obstacles.size();
    }

    @SuppressWarnings("SameReturnValue")
    public int getCupsCount(){
        return 12;
    }
    public Cups getCup(){
        return new Cups(12);
    }


}
