package objectgame;

import Util.Resource;
import userinterface.GameScreen;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyManager {
    private List<Enemy> enemies;
    private Random random;
    private BufferedImage obstacle1 , obstacle2;
    private MainCharacter mainCharacter;
    private GameScreen gameScreen;


    public EnemyManager(MainCharacter mainCharacter , GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.mainCharacter = mainCharacter;
        enemies = new ArrayList<Enemy>();
        obstacle1 = Resource.getResourceimage("data/rock.png");
        obstacle2 = Resource.getResourceimage("data/pot.png");
        random =new Random();

        enemies.add(getRandomObstacle());
        random = new Random();
    }

    public void update() {
        for (Enemy e : enemies) {
            e.update();
            if (e.isOver() && !e.isScoreGot()) {
                gameScreen.plusScore(20);
                e.setIsScoreGot(true);
            }
            if (e.getBound().intersects(mainCharacter.getBound())) {
                mainCharacter.setAlive(false);
            }
        }
        Enemy firstEnemy = enemies.get(0);
        if (firstEnemy.isOutOfScreen()) {
            enemies.remove(firstEnemy);
            enemies.add(getRandomObstacle());
        }
    }

    public void draw(Graphics g) {
        for (Enemy e : enemies) {
            e.draw(g);

        }
    }

    private Obstacles getRandomObstacle() {
        Obstacles obstacles;
        obstacles = new Obstacles(mainCharacter);
        obstacles.setX(600);
        if (random.nextBoolean()) {
            obstacles.setImage(obstacle1);
        }else obstacles.setImage(obstacle2);


        return obstacles;
    }
}
