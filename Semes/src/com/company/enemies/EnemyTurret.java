package com.company.enemies;

import com.company.objects.Wall;
import com.company.utils.Player;

import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;

public class EnemyTurret {
    private int enemyX;
    private int enemyY;

    private final int bulletSpeed = 4;
    private final int attackSpeed = 1000;
    private int damage = 25;
    private int health = 150;
    private int gold = 25;
    private ArrayList<Rectangle> upBullets;
    private ArrayList<Rectangle> downBullets;
    private ArrayList<Rectangle> rightBullets;
    private ArrayList<Rectangle> leftBullets;
    private Rectangle enemyRectangle;
    private Image image;

    private long lastShotTime = System.currentTimeMillis();

    /**
     * Konštruktor - Nastavenie pozicie x a y
     * @param x - pozicia na osi x
     * @param y - pozicia na osi y
     */
    public EnemyTurret(int x, int y) {
        this.upBullets = new ArrayList<>();
        this.downBullets = new ArrayList<>();
        this.rightBullets = new ArrayList<>();
        this.leftBullets = new ArrayList<>();
        this.enemyX = x;
        this.enemyY = y;
        this.enemyRectangle = new Rectangle(this.enemyX, this.enemyY, 16 * 3, 16 * 3);
        this.image = new ImageIcon("./pics/turrets/turret_all.png").getImage();
    }

    /**
     * metóda pomocou ktorej vykreslujeme objekt a gulky na JPanel
     * @param graphics kreslí na JPanel
     */
    public void paintComponent(Graphics graphics) {

        Graphics2D graphics2D = (Graphics2D)graphics;
        // vykreslenie enemy
        graphics2D.drawImage(this.image, this.enemyX, this.enemyY, 16 * 3, 16 * 3, null);
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

    /**
     * Getter
     * @return vracia hodnotu atribútu health
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Setter, nastavuje hodnotu atribútu health
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Getter
     * @return vracia hodnotu atribútu enemyRectangle
     */
    public Rectangle getEnemyRectangle() {
        return this.enemyRectangle;
    }

    /**
     * Getter
     * @return vracia hodnotu atribútu gold
     */
    public int getGold() {
        return this.gold;
    }

    /**
     * metóda pomocou ktorej nastavujeme začiatočnu polohu gulky a nastavujeme ako často môže strielať
     */
    public void enemyShoot() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.lastShotTime >= this.attackSpeed) {
            this.upBullets.add(new Rectangle(this.enemyX + (16 * 3) / 2, this.enemyY, 4, 8));
            this.downBullets.add(new Rectangle(this.enemyX + (16 * 3) / 2, this.enemyY + 16 * 3, 4, 8));
            this.rightBullets.add(new Rectangle(this.enemyX + 16 * 3, this.enemyY + (16 * 3) / 2, 8, 4));
            this.leftBullets.add(new Rectangle(this.enemyX, this.enemyY + (16 * 3) / 2, 8, 4));
            this.lastShotTime = currentTime;
        }
    }

    /**
     * metóda pomocou ktorej aktualizujeme polohu gulky a vymazavame ked prejde za okraj mapy
     */
    public void update() {
        int i = 0;
        while (i < this.upBullets.size()) {
            Rectangle bulletUp = this.upBullets.get(i);
            bulletUp.y -= this.bulletSpeed;
            if (bulletUp.y  < 0) {
                this.upBullets.remove(i);
                i--;
            }
            i++;
        }

        int j = 0;
        while (j < this.downBullets.size()) {
            Rectangle bulletDown = this.downBullets.get(j);
            bulletDown.y += this.bulletSpeed;

            if (bulletDown.y  > 576) {
                this.downBullets.remove(j);
                j--;
            }
            j++;
        }

        int k = 0;
        while (k < this.rightBullets.size()) {
            Rectangle bulletRight = this.rightBullets.get(k);
            bulletRight.x += this.bulletSpeed;

            if (bulletRight.x  > 768) {
                this.rightBullets.remove(k);
                k--;
            }
            k++;
        }

        int l = 0;
        while (l < this.leftBullets.size()) {
            Rectangle bulletLeft = this.leftBullets.get(l);
            bulletLeft.x -= this.bulletSpeed;

            if (bulletLeft.x  < 0) {
                this.leftBullets.remove(l);
                l--;
            }
            l++;
        }
    }
    //

    /**
     * metóda pomocou ktorej detekujeme či nepriatel zasiahol hrača
     * @return true ak áno, false ak nie
     */
    public boolean hasHit(Rectangle player) {
        for (int i = 0; i < this.upBullets.size(); i++) {
            if (this.upBullets.get(i).intersects(player)) {
                this.upBullets.remove(i);
                return true;
            }

        }

        for (int i = 0; i < this.downBullets.size(); i++) {
            if (this.downBullets.get(i).intersects(player)) {
                this.downBullets.remove(i);
                return true;
            }
        }

        for (int i = 0; i < this.rightBullets.size(); i++) {
            if (this.rightBullets.get(i).intersects(player)) {
                this.rightBullets.remove(i);
                return true;
            }
        }

        for (int i = 0; i < this.leftBullets.size(); i++) {
            if (this.leftBullets.get(i).intersects(player)) {
                this.leftBullets.remove(i);
                return true;
            }
        }

        return false;
    }


    /**
     * metóda pomocou ktorej, ak bol hrač zasiahnuty tak odoberame životy
     */
    public void hitPlayer(Player player) {

        Rectangle rect = player.getPlayerRectangle();
        if (this.hasHit(rect)) {
            player.setHealth(player.getHealth() - this.damage);
        }
    }

    /**
     * metóda pomocou ktorej, ak bola stena zasiahnuta tak vymazavame gulku
     */
    public void hitWall(ArrayList<Wall> walls) {
        for (int i = 0; i < walls.size(); i++) {
            for (int j = 0; j < walls.get(i).getRects().size(); j++) {
                if (this.hasHit(walls.get(i).getRects().get(j))) {
                    System.out.println("");
                }
            }
        }
    }
    //

}
