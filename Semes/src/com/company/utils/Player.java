package com.company.utils;

import java.awt.*;

public class Player {

    private Weapon weapon = new Weapon();

    private int health = 100;
    private int playerX = 100;
    private int playerY = 100;
    private int playerSpeed = 4;
    private Rectangle playerRectangle;

    public Player() {
        playerRectangle = new Rectangle(playerX, playerY, 16 * 3, 16 * 3);
    }

    // GETTERS SETTERS

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public Rectangle getPlayerRectangle() {
        return playerRectangle;
    }

    public void setPlayerRectangle(Rectangle playerRectangle) {
        this.playerRectangle = playerRectangle;
    }

    public int getPlayerSpeed() {
        return playerSpeed;
    }

    public void setPlayerSpeed(int playerSpeed) {
        this.playerSpeed = playerSpeed;
    }

    //

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
