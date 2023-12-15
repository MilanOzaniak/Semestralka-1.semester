package com.company;

import java.awt.*;

public class Player {
    Weapon weapon = new Weapon();
    int health = 100;
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    Rectangle playerRectangle;

    public Player() {
        playerRectangle = new Rectangle(playerX, playerY, 16 * 3, 16 * 3);
    }

    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(playerRectangle.x, playerRectangle.y, 16 * 3, 16 * 3);
        weapon.paintComponent(graphics2D);

    }

    public void updatePlayer(int x, int y) {
        playerRectangle.x += x;
        playerRectangle.y += y;
    }

    public void playerShoot() {
        weapon.shoot(playerRectangle.x, playerRectangle.y);
    }

    public void playerShootUpdate() {
        weapon.update();
    }

    public boolean hasCollided(Rectangle enemy) {
        for (int i = 0; i < weapon.bullets.size(); i++) {
            if(weapon.bullets.get(i).intersects(enemy)) {
                weapon.bullets.remove(i);
                System.out.println("PLAYERHIT");
                return true;
            }

        }
        return false;
    }
}
