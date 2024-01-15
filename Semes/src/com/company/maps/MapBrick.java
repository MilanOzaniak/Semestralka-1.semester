package com.company.maps;

import com.company.enemies.EnemyFast;
import com.company.enemies.EnemyLeft;
import com.company.enemies.EnemyTurret;
import com.company.objects.Wall;
import com.company.utils.Controller;
import com.company.utils.Player;
import com.company.windows.MainMenu;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MapBrick extends JPanel implements Runnable {

    private Thread thread;
    private final Controller controller;
    private final Player player;
    private boolean isFinished = false;

    private Wall wall = new Wall(0, 470, 1, "l", "brick");
    private Wall wall1 = new Wall(100, 520, 2, "h", "brick");
    private Wall wall2 = new Wall(100, 400, 1, "l", "brick");
    private Wall wall3 = new Wall(50, 330, 1, "l", "brick");
    private Wall wall4 = new Wall(0, 230, 1, "l", "brick");
    private Wall wall5 = new Wall(100, 230, 1, "l", "brick");
    private Wall wall6 = new Wall(100, 290, 1, "l", "brick");
    private Wall wall7 = new Wall(100, 130, 2, "l", "brick");
    private Wall wall8 = new Wall(0, 30, 1, "l", "brick");
    private Wall wall9 = new Wall(100, 60, 1, "l", "brick");
    private Wall wall10 = new Wall(200, 60, 1, "l", "brick");
    private Wall wall11 = new Wall(200, 130, 1, "l", "brick");
    private Wall wall12 = new Wall(200, 230, 1, "l", "brick");
    private Wall wall13 = new Wall(200, 330, 1, "l", "brick");
    private Wall wall14 = new Wall(200, 400, 1, "l", "brick");
    private Wall wall15 = new Wall(300, 180, 1, "l", "brick");
    private Wall wall16 = new Wall(300, 280, 1, "l", "brick");
    private Wall wall17 = new Wall(300, 380, 1, "l", "brick");
    private Wall wall18 = new Wall(300, 450, 1, "l", "brick");
    private Wall wall19 = new Wall(400, 0, 1, "l", "brick");
    private Wall wall20 = new Wall(400, 80, 4, "d", "brick");
    private Wall wall21 = new Wall(400, 310, 2, "d", "brick");
    private Wall wall22 = new Wall(400, 430, 1, "d", "brick");
    private Wall wall23 = new Wall(480, 430, 1, "d", "brick");
    private Wall wall24 = new Wall(560, 430, 1, "d", "brick");
    private Wall wall25 = new Wall(650, 430, 1, "d", "brick");

    private EnemyFast enemyFast = new EnemyFast(50, 0);
    private EnemyFast enemyFast1 = new EnemyFast(440, 0);
    private EnemyFast enemyFast2 = new EnemyFast(515, 0);
    private EnemyFast enemyFast3 = new EnemyFast(600, 0);

    private EnemyLeft enemyLeft = new EnemyLeft(150, 180);
    private EnemyLeft enemyLeft1 = new EnemyLeft(150, 360);
    private EnemyLeft enemyLeft2 = new EnemyLeft(150, 430);
    private EnemyLeft enemyLeft3 = new EnemyLeft(300, 100);
    private EnemyLeft enemyLeft4 = new EnemyLeft(720, 30);
    private EnemyLeft enemyLeft5 = new EnemyLeft(720, 270);
    private EnemyLeft enemyLeft6 = new EnemyLeft(720, 390);

    private EnemyTurret enemyTurret = new EnemyTurret(250, 230);


    private final ArrayList<EnemyTurret> enemies = new ArrayList<>();
    private final ArrayList<EnemyFast> enemies1 = new ArrayList<>();
    private final ArrayList<EnemyLeft> enemies2 = new ArrayList<>();
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Wall> walls = new ArrayList<>();

    // Nastavenia GUI (48x48, 768x576 px)
    private final int originalTileSize = 16;
    private final int scale = 3;

    private final int tileSize = this.originalTileSize * this.scale;
    private final int maxScreenCol = 16;
    private final int maxScreenRow = 12;
    private final int screenWidth = this.tileSize * this.maxScreenCol;
    private final int screenHeight = this.tileSize * this.maxScreenRow;
    private int fps = 60;
    private Image backgroundImage;
    private JFrame mainFrame;
    //

    /**
     * Konštruktor - Vykreslenie mapy
     * @param frame - na vytvorenie noveho JPanela
     * @param player - na udržanie toho isteho playera počas behu programu
     */
    public MapBrick(JFrame frame, Player player) {
        this.controller = new Controller();
        this.player = player;
        this.player.setPlayerPosition(0, 768);
        this.player.setHealth(100);
        this.mainFrame = frame;

        this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(this.controller);
        this.setFocusable(true);
        this.setLayout(null);
        this.backgroundImage = new ImageIcon("./pics/floor/stone_floor.png").getImage();

        this.players.add(this.player);
        this.enemies.add(this.enemyTurret);
        this.enemies1.add(this.enemyFast);
        this.enemies1.add(this.enemyFast1);
        this.enemies1.add(this.enemyFast2);
        this.enemies1.add(this.enemyFast3);
        this.enemies2.add(this.enemyLeft);
        this.enemies2.add(this.enemyLeft1);
        this.enemies2.add(this.enemyLeft2);
        this.enemies2.add(this.enemyLeft3);
        this.enemies2.add(this.enemyLeft4);
        this.enemies2.add(this.enemyLeft5);
        this.enemies2.add(this.enemyLeft6);
        this.walls.add(this.wall);
        this.walls.add(this.wall1);
        this.walls.add(this.wall2);
        this.walls.add(this.wall3);
        this.walls.add(this.wall4);
        this.walls.add(this.wall5);
        this.walls.add(this.wall6);
        this.walls.add(this.wall7);
        this.walls.add(this.wall8);
        this.walls.add(this.wall9);
        this.walls.add(this.wall10);
        this.walls.add(this.wall11);
        this.walls.add(this.wall12);
        this.walls.add(this.wall13);
        this.walls.add(this.wall14);
        this.walls.add(this.wall15);
        this.walls.add(this.wall16);
        this.walls.add(this.wall17);
        this.walls.add(this.wall18);
        this.walls.add(this.wall19);
        this.walls.add(this.wall20);
        this.walls.add(this.wall21);
        this.walls.add(this.wall22);
        this.walls.add(this.wall23);
        this.walls.add(this.wall24);
        this.walls.add(this.wall25);
    }

    /**
     * Metóda pomocou ktorej zapíname thread, aby bolo možné vykonavať viac veci sučastne
     */
    public void startGameThread() {
        this.thread = new Thread(this) ;
        this.thread.start();

    }

    /**
     * Hlavný "loop" hry, nastavujeme fps (60), vykonáva všetku logiku a vykresluje všetko na JPanel
     */
    @Override
    public void run() {
        double drawInterval = 1000000000 / this.fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (this.thread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                this.update();
                this.repaint();
                delta--;
            }
        }
    }


    /**
     * Metóda pomocou ktorej spravujeme všetku logiku, ovládanie a detekciu guliek
     */
    public void update() {
        this.player.playerHandler(this.controller);
        this.player.playerHitTurret(this.enemies);
        this.player.playerHitFast(this.enemies1);
        this.player.playerHitLeft(this.enemies2);
        this.player.playerHitWall(this.walls);

        for (int i = 0; i < this.walls.size(); i++) {
            this.walls.get(i).collided(this.player, this.controller);
        }

        for (int i = 0; i < this.enemies.size(); i++) {
            this.enemies.get(i).hitPlayer(this.player);
            this.enemies.get(i).hitWall(this.walls);

        }

        for (int i = 0; i < this.enemies1.size(); i++) {
            this.enemies1.get(i).hitPlayer(this.player);
            this.enemies1.get(i).hitWall(this.walls);
        }

        for (int i = 0; i < this.enemies2.size(); i++) {
            this.enemies2.get(i).hitPlayer(this.player);
            this.enemies2.get(i).hitWall(this.walls);
        }

        this.player.getPlayerRectangle().x = Math.max(0, Math.min(this.player.getPlayerRectangle().x, this.screenWidth - this.tileSize));
        this.player.getPlayerRectangle().y = Math.max(0, Math.min(this.player.getPlayerRectangle().y, this.screenHeight - this.tileSize));

        if (this.enemies.isEmpty() && this.enemies1.isEmpty() && this.enemies2.isEmpty() && !this.isFinished) {
            this.isFinished = true;

            this.returnToMenuHandler().actionPerformed(null);
            this.thread.stop();

        }

        if (this.player.getHealth() <= 0 && !this.isFinished) {
            this.isFinished = true;
            this.returnToMenuHandler().actionPerformed(null);
            this.thread.stop();
        }

    }

    /**
     * metóda pomocou ktorej vykreslujeme objekt a gulky na JPanel
     * @param graphics kreslí na JPanel
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D)graphics;

        graphics2D.drawImage(this.backgroundImage, 0, 0, null);

        for (int i = 0; i < this.players.size(); i++) {
            this.players.get(i).paintComponent(graphics2D);
        }

        for (int i = 0; i < this.walls.size(); i++) {
            this.walls.get(i).paintComponent(graphics2D);
        }

        for (int i = 0; i < this.enemies.size(); i++) {
            this.enemies.get(i).paintComponent(graphics2D);
        }

        for (int i = 0; i < this.enemies1.size(); i++) {
            this.enemies1.get(i).paintComponent(graphics2D);
        }

        for (int i = 0; i < this.enemies2.size(); i++) {
            this.enemies2.get(i).paintComponent(graphics2D);
        }

        graphics2D.dispose();
    }

    private ActionListener returnToMenuHandler() {

        return new ActionListener() {

            /**
             * metóda pomocou ktorej sa presuvame na iny JPanel (MainMenu)
             * @param e event spusteny stlačením tlačitka
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame window = new JFrame();
                MainMenu mainMenu = new MainMenu(window, MapBrick.this.player);


                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setResizable(false);
                window.setTitle("Shooting game");
                window.add(mainMenu);
                window.pack();
                window.setLocationRelativeTo(null);
                window.setVisible(true);
                MapBrick.this.mainFrame.dispose();
            }
        };
    }


}
