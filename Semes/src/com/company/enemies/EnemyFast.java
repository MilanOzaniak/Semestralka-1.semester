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

public class EnemyFast {
    private int enemyX;
    private int enemyY;

    private final int bulletSpeed = 5;
    private final int attackSpeed = 600;
    private int damage = 20;
    private int health = 70;
    private int gold = 15;
    private ArrayList<Rectangle> downBullets;
    private Rectangle enemyRectangle;
    private Image image;

    private long lastShotTime = System.currentTimeMillis();

    /**
     * Konštruktor - Nastavenie pozicie x a y
     * @param x - pozicia na osi x
     * @param y - pozicia na osi y
     */
    public EnemyFast(int x, int y) {
        this.downBullets = new ArrayList<Rectangle>();
        this.enemyX = x;
        this.enemyY = y;
        this.enemyRectangle = new Rectangle(this.enemyX , this.enemyY , 16 * 3 , 16 * 3);
        this.image = new ImageIcon("./pics/turrets/turret_down.png").getImage();

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
     * metóda pomocou ktorej vykreslujeme objekt a gulky na JPanel
     * @param graphics kreslí na JPanel
     */
    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.drawImage(this.image, this.enemyX, this.enemyY, 16 * 3, 16 * 3, null);

        graphics.setColor(Color.RED);

        for (Rectangle bullet : this.downBullets) {
            graphics.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
        }

    }

    /**
     * metóda pomocou ktorej nastavujeme začiatočnu polohu gulky a nastavujeme ako často môže strielať
     */
    public void enemyShoot() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.lastShotTime >= this.attackSpeed) {
            this.downBullets.add(new Rectangle(this.enemyX + (16 * 3) / 2, this.enemyY + 16 * 3, 4, 8));
            this.lastShotTime = currentTime;
        }
    }

    /**
     * metóda pomocou ktorej aktualizujeme polohu gulky a vymazavame ked prejde za okraj mapy
     */

    public void update() {
        ArrayList<Rectangle> bulletsToRemove = new ArrayList<>();

        for (Rectangle bulletDown : new ArrayList<>(this.downBullets)) {
            bulletDown.y += this.bulletSpeed;

            if (bulletDown.y > 576) {
                bulletsToRemove.add(bulletDown);
            }
        }

        this.downBullets.removeAll(bulletsToRemove);

    }

    /**
     * metóda pomocou ktorej detekujeme či nepriatel zasiahol hrača
     * @return true ak áno, false ak nie
     */
    public boolean hasHit(Rectangle player) {

        for (int i = 0; i < this.downBullets.size(); i++) {
            if (this.downBullets.get(i).intersects(player)) {
                this.downBullets.remove(i);
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


}
