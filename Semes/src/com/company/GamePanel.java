package com.company;

import com.company.enemies.EnemyFast;
import com.company.enemies.EnemyTurret;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    private Thread thread;
    Controller controller = new Controller();
    Player player = new Player();
    EnemyTurret enemyTurret = new EnemyTurret(70,70);
    EnemyTurret enemyTurret1 = new EnemyTurret(200,70);
    EnemyTurret enemyTurret2 = new EnemyTurret(350,70);
    EnemyFast enemyFast = new EnemyFast(100,100);
    EnemyFast enemyFast2 = new EnemyFast(150,100);
    EnemyFast enemyFast3 = new EnemyFast(200,100);

    ArrayList<EnemyTurret> enemies = new ArrayList<EnemyTurret>();
    ArrayList<EnemyFast> enemies1 = new ArrayList<EnemyFast>();
    ArrayList<Player> players = new ArrayList<Player>();

    // Nastavenia GUI (48x48, 768x576 px)
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    final int FPS = 60;
    //

    // nastavenia "Platna"
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(controller);
        this.setFocusable(true);
        enemies.add(enemyTurret);
        enemies1.add(enemyFast);
        enemies1.add(enemyFast2);
        enemies1.add(enemyFast3);
        players.add(player);

    }
    //

    public void startGameThread() {
        this.thread = new Thread(this) ;
        thread.start();

    }

    @Override
    public void run() {
       // nastavenie časovania
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(thread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
        //

    }

    public void update() {


        // väčšina logiky
        if (controller.upPressed) {
            player.updatePlayer(0, -player.playerSpeed);
        }else if (controller.downPressed) {
            player.updatePlayer(0, player.playerSpeed);
        }else if (controller.leftPressed) {
            player.updatePlayer(-player.playerSpeed, 0);
        }else if (controller.rightPressed) {
            player.updatePlayer(player.playerSpeed, 0);
        }

        // strielanie
        if(controller.spacePressed) {
            player.playerShoot();
        }
        //


        // update player a ak ho enemy trafi tak ho zabije
        for (int i = 0; i < players.size(); i++) {
            players.get(i).playerShootUpdate();

            for(int j =0; j < enemies.size(); j++) {
                if(enemies.get(j).hasCollided(players.get(i).playerRectangle)){

                    players.get(i).health -= enemies.get(j).getDamage();

                    if(players.get(i).health <= 0) {
                        players.remove(i);
                        JOptionPane.showMessageDialog(this, "You are dead! Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
                        break;

                    }
                }
            }
        }
        //


        //update enemies a ak hrač trafi enemy tak ho zabije
        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).enemyShoot();
            enemies.get(i).update();

            if(player.hasCollided(enemies.get(i).getEnemyRectangle())) {
                enemies.get(i).setHealth(enemies.get(i).getHealth() - player.weapon.damage);
                System.out.println(enemies.get(i).getHealth());
                if(enemies.get(i).getHealth() <= 0 ) {
                    enemies.remove(i);
                    i--;
                }
            }

        }


        //update enemies a ak hrač trafi enemy tak ho zabije
        //for(int i = 0; i < enemies1.size(); i++) {
        //    enemies1.get(i).enemyShoot();
        //    enemies1.get(i).update();
//
        //    if(player.hasCollided(enemies1.get(i).getEnemyRectangle())) {
        //        int health = enemies1.get(i).getHealth() - player.weapon.damage;
        //        if(health <= 0 ) {
        //            enemies1.remove(i);
        //            i--;
        //        }
        //    }
//
        //}
        //





        // ak hrač pojde za boundaries tak mu nastavi bud x = 0 alebo y = 0
        player.playerRectangle.x = Math.max(0, Math.min(player.playerRectangle.x, screenWidth - tileSize));
        player.playerRectangle.y = Math.max(0, Math.min(player.playerRectangle.y, screenHeight - tileSize));

        //


        // memory check

        //Runtime runtime = Runtime.getRuntime();
        //// Print total available memory
        //long totalMemory = runtime.totalMemory();
        //System.out.println("Total Memory: " + totalMemory / (1024 * 1024) + " MB");
//
        //// Print free memory
        //long freeMemory = runtime.freeMemory();
        //System.out.println("Free Memory: " + freeMemory / (1024 * 1024) + " MB");
//
        //// Print used memory
        //long usedMemory = totalMemory - freeMemory;
        //System.out.println("Used Memory: " + usedMemory / (1024 * 1024) + " MB");


    }

    public void paintComponent(Graphics graphics) {

        // tu kresliš na platno
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;


        // vykreslenie playera
        for (int i = 0; i < players.size(); i++) {
            players.get(i).paintComponent(graphics2D);
        }

        // vykreslenie enemies
        for (int i = 0; i < enemies.size(); i++){
            enemies.get(i).paintComponent(graphics2D);
        }

        //for (int i = 0; i < enemies1.size(); i++){
        //    enemies1.get(i).paintComponent(graphics2D);
        //}

        graphics2D.dispose();

        //
    }
}
