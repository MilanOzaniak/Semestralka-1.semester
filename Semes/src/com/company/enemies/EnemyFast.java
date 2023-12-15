package com.company.enemies;

import java.awt.*;
import java.util.ArrayList;

public class EnemyFast {
    private int enemyX;
    private int enemyY;

    private final int bulletSpeed = 5;
    private final int attackSpeed = 1200;
    private int damage = 15;
    private int health = 50;
    private ArrayList<Rectangle> downBullets;
    private Rectangle enemyRectangle;

    private long lastShotTime = System.currentTimeMillis();

    public EnemyFast(int x, int y) {
        this.downBullets = new ArrayList<Rectangle>();
        this.enemyX = x;
        this.enemyY = y;
        enemyRectangle = new Rectangle(enemyX, enemyY, 16*3,16*3);

    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Rectangle getEnemyRectangle() {
        return enemyRectangle;
    }

    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(enemyX, enemyY, 16 * 3, 16 * 3);

        graphics.setColor(Color.RED);

        for (Rectangle bullet : this.downBullets) {
            graphics.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
        }

    }

    public void enemyShoot() {
        long currentTime = System.currentTimeMillis();
        if(currentTime - this.lastShotTime >= this.attackSpeed) {
            this.downBullets.add(new Rectangle(this.enemyX + (16*3)/2, this.enemyY + 16*3, 4, 8));
            this.lastShotTime = currentTime;
        }
    }

    public void update() {

        for (int i = 0; i < this.downBullets.size(); i++) {
            Rectangle bulletDown = this.downBullets.get(i);
            bulletDown.y += this.bulletSpeed;

            if (bulletDown.y  > 576) {
                this.downBullets.remove(i);
                i--;
            }
        }

    }

    public boolean hasCollided(Rectangle player) {
        for (int i = 0; i < this.downBullets.size(); i++) {
            if (this.downBullets.get(i).intersects(player)) {
                this.downBullets.remove(i);
                System.out.println("DOWNHIT");
                return true;
            }
        }
        return false;
    }


}
