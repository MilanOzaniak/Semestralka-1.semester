package com.company.maps;

import com.company.windows.MainMenu;
import com.company.enemies.EnemyFast;
import com.company.enemies.EnemyLeft;
import com.company.enemies.EnemyTurret;
import com.company.objects.Wall;
import com.company.utils.Controller;
import com.company.utils.Player;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapDessert extends JPanel implements Runnable {

    private Thread thread;
    private final Controller controller;
    private Player player;
    private boolean isFinished = false;

    private Wall wall = new Wall(309, 213, 1, "l", "cactus");
    private Wall wall1 = new Wall(409, 213, 1, "l", "cactus");
    private Wall wall2 = new Wall(309, 313, 1, "l", "cactus");
    private Wall wall3 = new Wall(409, 313, 1, "l", "cactus");
    private Wall wall4 = new Wall(50, 470, 3, "p", "cactus");
    private Wall wall5 = new Wall(300, 520, 1, "p", "cactus");
    private Wall wall6 = new Wall(500, 520, 1, "p", "cactus");
    private Wall wall7 = new Wall(500, 420, 1, "p", "cactus");
    private Wall wall8 = new Wall(550, 370, 2, "p", "cactus");
    private Wall wall9 = new Wall(209, 213, 1, "h", "cactus");
    private Wall wall10 = new Wall(209, 113, 1, "h", "cactus");
    private Wall wall11 = new Wall(409, 113, 1, "h", "cactus");
    private Wall wall12 = new Wall(509, 113, 1, "h", "cactus");
    private Wall wall13 = new Wall(0, 0, 1, "h", "cactus");

    private EnemyTurret enemyTurret = new EnemyTurret(359, 263);
    private EnemyTurret enemyTurret1 = new EnemyTurret(459, 163);
    private EnemyTurret enemyTurret2 = new EnemyTurret(259, 63);

    private EnemyFast enemyFast = new EnemyFast(50, 0);

    private EnemyLeft enemyLeft = new EnemyLeft(650, 470);
    private EnemyLeft enemyLeft1 = new EnemyLeft(720, 113);



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
    private final int fps = 60;
    private Image backgroundImage;
    private JFrame mainFrame;
    //

    /**
     * Konštruktor - Vykreslenie mapy
     * @param frame - na vytvorenie noveho JPanela
     * @param player - na udržanie toho isteho playera počas behu programu
     */
    public MapDessert (JFrame frame, Player player) {
        this.controller = new Controller();
        this.player = player;
        this.player.setPlayerPosition(0, 768);
        this.player.setHealth(100);
        this.mainFrame = frame;

        this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(this.controller);
        this.setFocusable(true);
        this.backgroundImage = new ImageIcon("./pics/floor/sand_floor.png").getImage();

        this.players.add(player);
        this.enemies.add(this.enemyTurret);
        this.enemies.add(this.enemyTurret1);
        this.enemies.add(this.enemyTurret2);
        this.enemies1.add(this.enemyFast);
        this.enemies2.add(this.enemyLeft);
        this.enemies2.add(this.enemyLeft1);
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
                MainMenu mainMenu = new MainMenu(window, MapDessert.this.player);


                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setResizable(false);
                window.setTitle("Shooting game");
                window.add(mainMenu);
                window.pack();
                window.setLocationRelativeTo(null);
                window.setVisible(true);
                MapDessert.this.mainFrame.dispose();
            }
        };
    }
}
