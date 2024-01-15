package com.company.utils;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Weapon {


    private int bulletSpeed = 6;
    private int attackSpeed;
    private int damage;
    private ArrayList<Rectangle> bullets;
    private Color color;
    private long lastShotTime = System.currentTimeMillis();


    /**
     * Konštruktor
     * @param damage - sila zbrane
     * @param attackSpeed - rychlosť útoku zbrane
     * @param color - farba gulky
     */
    public Weapon(int damage, int attackSpeed, Color color) {
        this.color = color;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.bullets = new ArrayList<>();
    }

    /**
     *  Getter
     * @return vracia hodnotu atribútu damage
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     *  Getter
     * @return vracia hodnotu zoznamu bullets
     */
    public ArrayList<Rectangle> getBullets() {
        return this.bullets;
    }


    /**
     * Metóda pomocou ktorej nastavujeme začiatočnu poziciu gulky
     * @param playerX - os x
     * @param playerY - os y
     */
    public void shoot(int playerX, int playerY) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.lastShotTime >= this.attackSpeed) {
            this.bullets.add(new Rectangle(playerX + (16 * 3) / 2, playerY, 4, 8));
            this.lastShotTime = currentTime;

        }
    }

    /**
     * Metóda pomoocu ktorej posúvame gulku po osi Y
     */
    public void update() {
        ArrayList<Rectangle> bulletsToRemove = new ArrayList<>();

        for (Rectangle bullet : new ArrayList<>(this.bullets)) {
            bullet.y -= this.bulletSpeed;

            if (bullet.y + bullet.height < 0) {
                bulletsToRemove.add(bullet);
            }
        }

        this.bullets.removeAll(bulletsToRemove);
    }

    /**
     * metóda pomocou ktorej vykreslujeme gulky na JPanel
     * @param graphics kreslí na JPanel
     */
    public void paintComponent(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        for (Rectangle bullet : this.bullets) {
            graphics.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
        }
    }

}
