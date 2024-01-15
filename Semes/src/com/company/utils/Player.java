package com.company.utils;

import com.company.enemies.EnemyFast;
import com.company.enemies.EnemyLeft;
import com.company.enemies.EnemyTurret;
import com.company.objects.Wall;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Player {

    private Weapon weapon = new Weapon(50, 400, Color.BLACK);

    private int health = 100;
    private int playerX = 0;
    private int playerY = 768;
    private int playerSpeed = 4;
    private Rectangle playerRectangle;
    private int gold = 0;
    private Image playerImage;

    /**
     * Konštruktor
     * Nastavujeme obrazok a Rectangle hráča
     */
    public Player() {
        this.playerRectangle = new Rectangle(this.playerX, this.playerY, 16 * 3, 16 * 3);
        this.playerImage = new ImageIcon("./pics/player/player_m4.png").getImage();
    }

    /**
     *  Getter
     * @return vracia hodnotu atribútu weapon
     */
    public Weapon getWeapon() {
        return this.weapon;
    }

    /**
     * Setter, nastavuje hodnotu atribútu weapon
     * @param weapon
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     *  Getter
     * @return vracia hodnotu atribútu health
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Setter, nastavuje hodnotu atribútu  health
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     *  Getter
     * @return vracia hodnotu atribútu playerRectangle
     */
    public Rectangle getPlayerRectangle() {
        return this.playerRectangle;
    }

    /**
     *  Getter
     * @return vracia hodnotu atribútu playerSpeed
     */
    public int getPlayerSpeed() {
        return this.playerSpeed;
    }

    /**
     * Setter, nastavuje hodnotu atribútu playerImage
     * @param playerImage
     */
    public void setPlayerImage(Image playerImage) {
        this.playerImage = playerImage;
    }

    /**
     *  Getter
     * @return vracia hodnotu atribútu gold
     */
    public int getGold() {
        return this.gold;
    }

    /**
     * Setter, nastavuje hodnotu atribútu gold
     * @param gold
     */
    public void setGold(int gold) {
        this.gold = gold;
    }

    /**
     * metóda pomocou ktorej vykreslujeme objekt na JPanel
     * @param graphics kreslí na JPanel
     */
    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.drawImage(this.playerImage , this.playerRectangle.x, this.playerRectangle.y, 16 * 3, 16 * 3, null);
        this.weapon.paintComponent(graphics2D);

    }

    /**
     * metóda pomocou aktualizujeme polohu hrača na JPaneli
     */
    public void updatePlayer(int x, int y) {
        this.playerRectangle.x += x;
        this.playerRectangle.y += y;
    }

    /**
     * metóda pomocou nastavujeme polohu hrača na JPaneli
     * @param x - os x
     * @param y - os y
     */
    public void setPlayerPosition(int x, int y) {
        this.playerRectangle.x = x;
        this.playerRectangle.y = y;
    }

    /**
     * metóda pomocou ktorej nastavujeme začiatočnu poziciu gulky
     */
    public void playerShoot() {
        this.weapon.shoot(this.playerRectangle.x, this.playerRectangle.y);

    }

    /**
     * metóda pomocou ktorej volame metodu pohybu gulky po JPaneli
     */
    public void playerShootUpdate() {
        this.weapon.update();
    }

    /**
     * metóda pomocou ktorej ovladame pohyb hrača
     * @param controller - ziskanie vstupu z klavesnice
     */
    public void playerHandler(Controller controller) {

        if (controller.isUpPressed()) {
            this.updatePlayer(0, -this.getPlayerSpeed());
        } else if (controller.isDownPressed()) {
            this.updatePlayer(0, this.getPlayerSpeed());
        } else if (controller.isLeftPressed()) {
            this.updatePlayer(-this.getPlayerSpeed(), 0);
        } else if (controller.isRightPressed()) {
            this.updatePlayer(this.getPlayerSpeed(), 0);
        }

        if (controller.isSpacePressed()) {
            this.playerShoot();
        }

        this.playerShootUpdate();


    }

    /**
     * metóda pomocou detekujeme či hráč zasiahol nepriatela
     * @return true ak áno, false ak nie
     */
    public boolean hasHit(Rectangle rect) {
        for (int i = 0; i < this.weapon.getBullets().size(); i++) {
            if (this.weapon.getBullets().get(i).intersects(rect)) {
                this.weapon.getBullets().remove(i);
                return true;
            }

        }
        return false;
    }

    /**
     * metóda pomocou ktorej detekujeme zasiahnutie nepriatela
     * @param enemies
     */

    public void playerHitTurret(ArrayList<EnemyTurret> enemies) {
        ArrayList<EnemyTurret> enemiesToRemove = new ArrayList<>();

        for (EnemyTurret enemy : enemies) {
            enemy.enemyShoot();
            enemy.update();

            if (this.hasHit(enemy.getEnemyRectangle())) {
                enemy.setHealth(enemy.getHealth() - this.getWeapon().getDamage());
                if (enemy.getHealth() <= 0) {
                    this.gold += enemy.getGold();
                    enemiesToRemove.add(enemy);
                }
            }
        }

        enemies.removeAll(enemiesToRemove);
    }

    /**
     * metóda pomocou ktorej detekujeme zasiahnutie nepriatela
     * @param enemies
     */
    public void playerHitFast(ArrayList<EnemyFast> enemies) {
        ArrayList<EnemyFast> enemiesToRemove = new ArrayList<>();

        for (EnemyFast enemy : enemies) {
            enemy.enemyShoot();
            enemy.update();

            if (this.hasHit(enemy.getEnemyRectangle())) {
                enemy.setHealth(enemy.getHealth() - this.getWeapon().getDamage());
                if (enemy.getHealth() <= 0) {
                    this.gold += enemy.getGold();
                    enemiesToRemove.add(enemy);
                }
            }
        }

        enemies.removeAll(enemiesToRemove);
    }

    /**
     * metóda pomocou ktorej detekujeme zasiahnutie nepriatela
     * @param enemies
     */
    public void playerHitLeft(ArrayList<EnemyLeft> enemies) {
        ArrayList<EnemyLeft> enemiesToRemove = new ArrayList<>();

        for (EnemyLeft enemy : enemies) {
            enemy.enemyShoot();
            enemy.update();

            if (this.hasHit(enemy.getEnemyRectangle())) {
                enemy.setHealth(enemy.getHealth() - this.getWeapon().getDamage());
                if (enemy.getHealth() <= 0) {
                    this.gold += enemy.getGold();
                    enemiesToRemove.add(enemy);
                }
            }
        }

        enemies.removeAll(enemiesToRemove);
    }

    /**
     * metóda pomocou ktorej detekujeme zasiahnutie steny
     * @param walls
     */
    public void playerHitWall(ArrayList<Wall> walls) {
        for (int i = 0; i < walls.size(); i++) {
            for (int j = 0; j < walls.get(i).getRects().size(); j++) {
                if (this.hasHit(walls.get(i).getRects().get(j))) {
                    System.out.println("");
                }
            }
        }
    }
}
