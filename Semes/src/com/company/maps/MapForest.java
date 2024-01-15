package com.company.maps;

import com.company.windows.MainMenu;
import com.company.enemies.EnemyFast;
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

public class MapForest extends JPanel implements Runnable {

    private Thread thread;
    private final Controller controller;
    private final Player player;
    private boolean isFinished = false;

    private Wall wall = new Wall(100, 100, 1, "l", "tree");
    private Wall wall1 = new Wall(150, 150, 1, "l", "tree");
    private Wall wall2 = new Wall(200, 200, 1, "l", "tree");
    private Wall wall3 = new Wall(150, 250, 1, "l", "tree");
    private Wall wall4 = new Wall(0, 300, 2, "p", "tree");
    private Wall wall5 = new Wall(0, 450, 5, "p", "tree");
    private Wall wall6 = new Wall(400, 450, 4, "p", "tree");
    private Wall wall7 = new Wall(400, 380, 3, "p", "tree");
    private Wall wall8 = new Wall(350, 310, 3, "p", "tree");
    private Wall wall9 = new Wall(0, 50, 7, "p", "tree");
    private Wall wall10 = new Wall(350, 50, 3, "p", "tree");
    private Wall wall11 = new Wall(510, 50, 5, "p", "tree");
    private Wall wall12 = new Wall(350, 200, 1, "p", "tree");
    private Wall wall13 = new Wall(450, 200, 1, "p", "tree");
    private Wall wall14 = new Wall(560, 100, 4, "d", "tree");
    private Wall wall15 = new Wall(100, 400, 1, "p", "tree");
    private Wall wall16 = new Wall(400, 500, 2, "d", "tree");

    private EnemyTurret enemyTurret = new EnemyTurret(400, 150);
    private EnemyFast enemyFast = new EnemyFast(320, 0);
    private EnemyFast enemyFast1 = new EnemyFast(480, 0);
    private EnemyFast enemyFast2 = new EnemyFast(100, 150);




    private final ArrayList<EnemyTurret> enemies = new ArrayList<EnemyTurret>();
    private final ArrayList<EnemyFast> enemies1 = new ArrayList<EnemyFast>();
    private final ArrayList<Player> players = new ArrayList<Player>();
    private final ArrayList<Wall> walls = new ArrayList<Wall>();

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
    public MapForest(JFrame frame, Player player) {
        this.controller = new Controller();
        this.player = player;
        this.mainFrame = frame;
        this.player.setPlayerPosition(0, 768);
        this.player.setHealth(100);

        this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(this.controller);
        this.setFocusable(true);
        this.backgroundImage = new ImageIcon("./pics/floor/grass_floor.png").getImage();

        this.players.add(this.player);
        this.enemies.add(this.enemyTurret);
        this.enemies1.add(this.enemyFast);
        this.enemies1.add(this.enemyFast1);
        this.enemies1.add(this.enemyFast2);
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
     */    @Override
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

        this.player.playerHitTurret(this.enemies);
        this.player.playerHitFast(this.enemies1);
        this.player.playerHitWall(this.walls);

        this.player.getPlayerRectangle().x = Math.max(0, Math.min(this.player.getPlayerRectangle().x, this.screenWidth - this.tileSize));
        this.player.getPlayerRectangle().y = Math.max(0, Math.min(this.player.getPlayerRectangle().y, this.screenHeight - this.tileSize));

        if (this.enemies.isEmpty() && this.enemies1.isEmpty() && !this.isFinished) {
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

        for (int i = 0; i < this.enemies.size(); i++) {
            this.enemies.get(i).paintComponent(graphics2D);
        }

        for (int i = 0; i < this.enemies1.size(); i++) {
            this.enemies1.get(i).paintComponent(graphics2D);
        }


        for (int i = 0; i < this.walls.size(); i++) {
            this.walls.get(i).paintComponent(graphics2D);
        }

        graphics2D.dispose();
    }

    // Po skončeni hry sa vraciame do main menu
    private ActionListener returnToMenuHandler() {

        /**
         * metóda pomocou ktorej sa presuvame na iny JPanel (MainMenu)
         * @param e event spusteny stlačením tlačitka
         */
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame window = new JFrame();
                MainMenu mainMenu = new MainMenu(window, MapForest.this.player);


                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setResizable(false);
                window.setTitle("Shooting game");
                window.add(mainMenu);
                window.pack();
                window.setLocationRelativeTo(null);
                window.setVisible(true);
                MapForest.this.mainFrame.dispose();
            }
        };
    }

}
