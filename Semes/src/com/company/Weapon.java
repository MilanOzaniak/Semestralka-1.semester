package com.company;

import java.awt.*;
import java.util.ArrayList;

public class Weapon {
    final int bulletSpeed = 4;
    final int attackSpeed = 500;
    final int damage = 40;
    ArrayList<Rectangle> bullets;
    long lastShotTime = System.currentTimeMillis();

    public Weapon() {
        this.bullets = new ArrayList<Rectangle>();
    }

    // vykreslenie pozicie kde začinaju bullets a nastavenie attack speedu
    public void shoot(int playerX, int playerY) {
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastShotTime >= attackSpeed) {
            bullets.add(new Rectangle(playerX + (16*3)/2, playerY, 4, 8));
            lastShotTime = currentTime;

        }
    }

    // updatovanie bulletov a nastavenie pohybu bulletov
    public void update() {
        for (int i = 0; i < bullets.size(); i++) {
            Rectangle bullet = bullets.get(i);
            bullet.y -= bulletSpeed;

            if (bullet.y + bullet.height < 0) {
                bullets.remove(i);
                i--;
            }
        }
    }

    // vykreslovanie bulletov
    public void paintComponent(Graphics graphics) {
        graphics.setColor(Color.RED);
        for (Rectangle bullet : bullets) {
            graphics.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
        }
    }

}
