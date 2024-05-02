package userinterface;

import javax.swing.*;

public class GameWindow extends JFrame {
    private GameScreen gameScreen;
    public GameWindow(){
        super("Jump From Obstacle");
        setSize(800, 250);
        setLocation(600,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameScreen = new GameScreen();
        add(gameScreen);
        addKeyListener(gameScreen);
    }
    public  void startGame(){
        gameScreen.startGame();
    }
    public static void main(String[] args) {
        GameWindow gw =new GameWindow();
        gw.setVisible(true);
        gw.startGame();
    }

}
