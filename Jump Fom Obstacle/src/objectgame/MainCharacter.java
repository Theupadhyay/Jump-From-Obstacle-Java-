package objectgame;

import Util.Animation;
import Util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

import static userinterface.GameScreen.GRAVITY;
import static userinterface.GameScreen.GROUNDY;

public class MainCharacter {
    private float x = 0;
    private float y = 0;
    private float speedY= 0;
    private Animation characterRun;
    private Rectangle rect;
    private boolean isAlive = true;

    public MainCharacter() {
        characterRun = new Animation(100);
        characterRun.addFrame(Resource.getResourceimage("data/man11.png"));
        characterRun.addFrame(Resource.getResourceimage("data/man2.jpeg"));
        characterRun.addFrame(Resource.getResourceimage("data/man3.jpeg"));
        rect = new Rectangle();
    }

    public void update(){
        characterRun.update();
        // this is for jumping
        if (y>= GROUNDY -characterRun.getFrame().getHeight()){
            speedY =0;
            y = GROUNDY -characterRun.getFrame().getHeight();
        }else {
            speedY += GRAVITY;
            y += speedY;
        }
        rect.x = (int)x ;
        rect.y = (int)y ;
        rect.width = characterRun.getFrame().getWidth();
        rect.height = characterRun.getFrame().getHeight();
    }

    public Rectangle getBound() {
        return rect;
    }
    public  void draw(Graphics g){
        g.setColor(Color.BLACK);
//        g.drawRect((int)x,(int )y, characterRun.getFrame().getWidth(), characterRun.getFrame().getHeight());
        g.drawImage(characterRun.getFrame(), (int) x, (int) y, null);
    }

    public void jump(){
        speedY = -4;
        y += speedY;
    }
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean getAlive() {
        return isAlive;
    }

}
