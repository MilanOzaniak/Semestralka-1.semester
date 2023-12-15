package com.company.enemies;

import java.awt.*;
import java.util.ArrayList;

public class EnemyTurret {
    private int enemyX;
    private int enemyY;

    private final int bulletSpeed = 4;
    private final int attackSpeed = 700;
    private int damage = 10;
    private int health = 100;
    private ArrayList<Rectangle> upBullets;
    private ArrayList<Rectangle> downBullets;
    private ArrayList<Rectangle> rightBullets;
    private ArrayList<Rectangle> leftBullets;
    private Rectangle enemyRectangle;

    private long lastShotTime = System.currentTimeMillis();

    public EnemyTurret(int x, int y) {
        this.upBullets = new ArrayList<Rectangle>();
        this.downBullets = new ArrayList<Rectangle>();
        this.rightBullets = new ArrayList<Rectangle>();
        this.leftBullets = new ArrayList<Rectangle>();
        this.enemyX = x;
        this.enemyY = y;
        enemyRectangle = new Rectangle(enemyX, enemyY, 16*3,16*3);
    }

    public void paintComponent(Graphics graphics) {

        Graphics2D graphics2D = (Graphics2D) graphics;
        // vykreslenie enemy
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(enemyX, enemyY, 16 * 3, 16 * 3);
        //

        // vykreslenie enemy bullet
        graphics.setColor(Color.RED);

        // vykreslenie upBullets
        for (Rectangle bullet : this.upBullets) {
            graphics.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
        }

        // vykreslenie downBullets
        for (Rectangle bullet : this.downBullets) {
            graphics.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
        }

        // vykreslenie rightBullets
        for (Rectangle bullet : this.rightBullets) {
            graphics.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
        }

        // vykreslenie leftBullets
        for (Rectangle bullet : this.leftBullets) {
            graphics.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
        }

    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    public int getDamage() {
        return this.damage;
    }

    public Rectangle getEnemyRectangle() {
        return this.enemyRectangle;
    }

    // vykreslenie pozicie kde začinaju bullets a nastavenie attack speedu
    public void enemyShoot() {
        long currentTime = System.currentTimeMillis();
        if(currentTime - this.lastShotTime >= this.attackSpeed) {
            this.upBullets.add(new Rectangle(this.enemyX + (16*3)/2, this.enemyY, 4, 8));
            this.downBullets.add(new Rectangle(this.enemyX + (16*3)/2, this.enemyY + 16*3, 4, 8));
            this.rightBullets.add(new Rectangle(this.enemyX + 16*3, this.enemyY + (16*3) / 2, 8, 4));
            this.leftBullets.add(new Rectangle(this.enemyX, this.enemyY + (16*3)/2, 8, 4));
            this.lastShotTime = currentTime;
        }
    }
    //

    // updatovanie bulletov a nastavenie pohybu bulletov
    public void update() {
        for (int i = 0; i < this.upBullets.size(); i++) {
            Rectangle bulletUp = this.upBullets.get(i);
            bulletUp.y -= this.bulletSpeed;

            if (bulletUp.y  < 0) {
                this.upBullets.remove(i);
                i--;
            }
        }

        for (int i = 0; i < this.downBullets.size(); i++) {
            Rectangle bulletDown = this.downBullets.get(i);
            bulletDown.y += this.bulletSpeed;

            if (bulletDown.y  > 576) {
                this.downBullets.remove(i);
                i--;
            }
        }

        for (int i = 0; i < this.rightBullets.size(); i++) {
            Rectangle bulletRight = this.rightBullets.get(i);
            bulletRight.x += this.bulletSpeed;

            if (bulletRight.x  > 768) {
                this.rightBullets.remove(i);
                i--;
            }
        }

        for (int i = 0; i < this.leftBullets.size(); i++) {
            Rectangle bulletLeft = this.leftBullets.get(i);
            bulletLeft.x -= this.bulletSpeed;

            if (bulletLeft.x  < 0) {
                this.leftBullets.remove(i);
                i--;
            }
        }
    }
    //

    // detekcia hitnutia bulletu
    public boolean hasCollided(Rectangle player) {
        for (int i = 0; i < this.upBullets.size(); i++) {
            if (this.upBullets.get(i).intersects(player)) {
                this.upBullets.remove(i);
                System.out.println("UPHIT");
                return true;
            }

        }

        for (int i = 0; i < this.downBullets.size(); i++) {
            if (this.downBullets.get(i).intersects(player)) {
                this.downBullets.remove(i);
                System.out.println("DOWNHIT");
                return true;
            }
        }

        for (int i = 0; i < this.rightBullets.size(); i++) {
            if (this.rightBullets.get(i).intersects(player)) {
                this.rightBullets.remove(i);
                System.out.println("RIGHTHIT");
                return true;
            }
        }

        for (int i = 0; i < this.leftBullets.size(); i++) {
            if (this.leftBullets.get(i).intersects(player)) {
                this.leftBullets.remove(i);
                System.out.println("LEFTHIT");
                return true;
            }
        }

        //System.out.println("NOHIT");
        return false;
    }
    //

}
