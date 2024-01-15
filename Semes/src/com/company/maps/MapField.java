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


public class MapField extends JPanel implements Runnable {

    private Thread thread;
    private final Controller controller;
    private final Player player;
    private boolean isFinished = false;

    private Wall wall = new Wall(100, 100, 1, "l", "tree");
    private Wall wall1 = new Wall(678, 100, 1, "l", "tree");
    private Wall wall2 = new Wall(150, 150, 1, "p", "rock");
    private Wall wall3 = new Wall(220, 150, 1, "p", "rock");
    private Wall wall4 = new Wall(150, 220, 1, "p", "rock");
    private Wall wall5 = new Wall(220, 220, 1, "p", "rock");
    private Wall wall6 = new Wall(528, 150, 1, "p", "rock");
    private Wall wall7 = new Wall(458, 150, 1, "p", "rock");
    private Wall wall8 = new Wall(528, 220, 1, "p", "rock");
    private Wall wall9 = new Wall(458, 220, 1, "p", "rock");
    private Wall wall10 = new Wall(290, 150, 1, "p", "rock");
    private Wall wall11 = new Wall(290, 220, 1, "p", "rock");
    private Wall wall12 = new Wall(388, 150, 1, "p", "rock");
    private Wall wall13 = new Wall(388, 220, 1, "p", "rock");
    private Wall wall14 = new Wall(150, 320, 1, "p", "rock");
    private Wall wall15 = new Wall(150, 390, 1, "p", "rock");
    private Wall wall16 = new Wall(150, 460, 1, "d", "rock");
    private Wall wall17 = new Wall(220, 320, 1, "p", "rock");
    private Wall wall18 = new Wall(220, 390, 1, "p", "rock");
    private Wall wall19 = new Wall(220, 460, 1, "p", "rock");
    private Wall wall20 = new Wall(290, 320, 1, "p", "rock");
    private Wall wall21 = new Wall(290, 390, 1, "p", "rock");
    private Wall wall22 = new Wall(290, 460, 1, "p", "rock");
    private Wall wall23 = new Wall(528, 320, 1, "p", "rock");
    private Wall wall24 = new Wall(528, 390, 1, "p", "rock");
    private Wall wall25 = new Wall(528, 460, 1, "p", "rock");
    private Wall wall26 = new Wall(458, 320, 1, "p", "rock");
    private Wall wall27 = new Wall(388, 320, 1, "p", "rock");
    private Wall wall28 = new Wall(388, 460, 1, "p", "rock");
    private Wall wall29 = new Wall(338, 520, 1, "p", "tree");

    private EnemyFast enemyFast = new EnemyFast(185, 100);
    private EnemyFast enemyFast1 = new EnemyFast(255, 100);
    private EnemyFast enemyFast2 = new EnemyFast(425, 100);
    private EnemyFast enemyFast3 = new EnemyFast(495, 100);

    private EnemyTurret enemyTurret = new EnemyTurret(340, 270);

    private EnemyLeft enemyLeft = new EnemyLeft(718, 50);
    private EnemyLeft enemyLeft1 = new EnemyLeft(578, 180);
    private EnemyLeft enemyLeft2 = new EnemyLeft(578, 355);
    private EnemyLeft enemyLeft3 = new EnemyLeft(578, 420);




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
    public MapField (JFrame frame, Player player) {


        this.controller = new Controller();
        this.player = player;
        this.player.setPlayerPosition(0, 768);
        this.player.setHealth(100);
        this.mainFrame = frame;

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
        this.enemies1.add(this.enemyFast3);
        this.enemies2.add(this.enemyLeft);
        this.enemies2.add(this.enemyLeft1);
        this.enemies2.add(this.enemyLeft2);
        this.enemies2.add(this.enemyLeft3);
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
        this.walls.add(this.wall26);
        this.walls.add(this.wall27);
        this.walls.add(this.wall28);
        this.walls.add(this.wall29);
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

        /**
         * metóda pomocou ktorej sa presuvame na iny JPanel (MainMenu)
         * @param e event spusteny stlačením tlačitka
         */

        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame window = new JFrame();
                MainMenu mainMenu = new MainMenu(window, MapField.this.player);


                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setResizable(false);
                window.setTitle("Shooting game");
                window.add(mainMenu);
                window.pack();
                window.setLocationRelativeTo(null);
                window.setVisible(true);
                MapField.this.mainFrame.dispose();
            }
        };
    }
}
