package userinterface;

import Util.Resource;
import objectgame.*;

import javax.management.StringValueExp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GameScreen extends JPanel implements Runnable , KeyListener {
    public static final int  GAME_FIRST_STAGE =0;
    public static final int  GAME_PLAY_STAGE =1;
    public static final int  GAME_OVER_STAGE =2;
    public static final float GRAVITY = 0.1f;
    public static final float GROUNDY = 160 ; // in Pixel
    private MainCharacter mainCharacter;
    private  int gameStage = GAME_FIRST_STAGE;
    private BufferedImage imageGameOverText;

    private Thread thread;
    private Land land;
    private Clouds cloud;
    private EnemyManager enemyManager;
    private int  score;
    public GameScreen(){
        thread = new Thread(this);
        mainCharacter = new MainCharacter();
        mainCharacter.setX(90);
        mainCharacter.setY(90);
        land = new Land(this);
        cloud = new Clouds();
        enemyManager = new EnemyManager(mainCharacter ,this );
        imageGameOverText = Resource.getResourceimage("data/gameover_text.png");

    }

    public void startGame() {
        thread.start();
    }
    @Override
    public void run() {
        while(true){
            try {
                update();
                repaint();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        switch (gameStage) {
            case GAME_PLAY_STAGE :
                mainCharacter.update();
                land.upadate();
                cloud.update();
                enemyManager.update();
                if (!mainCharacter.getAlive()) {
                    gameStage = GAME_OVER_STAGE;
                }
                break;
        }

    }

    public void plusScore(int score) {
        this.score+= score;
    }

    public void paint(Graphics g){
        g.setColor(Color.decode("#f7f7f7"));
        g.fillRect(0,0,getWidth(),getHeight());
//        g.setColor(Color.RED);
//        g.drawLine(0,(int)GROUNDY,getWidth(),(int)GROUNDY);

        switch (gameStage) {
            case GAME_FIRST_STAGE :
                mainCharacter.draw(g);
                break;
            case GAME_PLAY_STAGE:
                cloud.draw(g);
                land.dwaw(g);
                mainCharacter.draw(g);
                enemyManager.draw(g);
                g.drawString("HI "+String.valueOf(score),700,10);
                break;
            case GAME_OVER_STAGE:
                cloud.draw(g);
                land.dwaw(g);
                mainCharacter.draw(g);
                enemyManager.draw(g);
                g.drawImage(imageGameOverText,300,80,null);
                break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                if (gameStage == GAME_FIRST_STAGE) {
                    gameStage = GAME_PLAY_STAGE;
                } else if (gameStage == GAME_PLAY_STAGE) {
                    mainCharacter.jump();
                } else if (gameStage == GAME_OVER_STAGE) {
                    gameStage = GAME_PLAY_STAGE;
                }
                break;
        }
    }
}
