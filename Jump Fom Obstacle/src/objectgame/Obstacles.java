package objectgame;

import Util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Obstacles extends Enemy{
    public BufferedImage image;
    private int posX , posY;
    private Rectangle rect;
    private MainCharacter mainCharacter;
    private boolean isScoreGot = false;

    public Obstacles(MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
        image = Resource.getResourceimage("data/rock.png");
        posX = 300;
        posY = 140;
        rect = new Rectangle();
    }

    public void update() {
        posX -= 2;
        rect.x =posX;
        rect.y = posY;
        rect.width =  image.getWidth();
        rect.height = image.getHeight();
    }

    public Rectangle getBound() {
        return rect;
    }
    public void draw(Graphics g) {
        g.drawImage(image, posX, posY, null);
    }

    public void setX(int x) {
        posX = x;
    }

    public void setY(int y) {
        posY = y;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean isOutOfScreen() {
      return  (posX+ image.getWidth() < 0 );
    }

    public boolean isOver() {
        return (mainCharacter.getX() >posX);
    }

    public boolean isScoreGot() {
        return isScoreGot;
    }


    public void setIsScoreGot(boolean isScoreGot) {
        this.isScoreGot = isScoreGot ;
    }
}
