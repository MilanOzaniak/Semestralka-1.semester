package com.company.windows;

import com.company.maps.MapDessert;
import com.company.maps.MapBrick;
import com.company.maps.MapField;
import com.company.maps.MapForest;
import com.company.maps.MapSnow;
import com.company.utils.Player;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {

    private JFrame mainFrame;
    private JLabel titleLabel;
    private JLabel levelsLabel;
    private JLabel goldLabel;
    private JButton buttonShop;
    private JButton lvl1;
    private JButton lvl2;
    private JButton lvl3;
    private JButton lvl4;
    private JButton lvl5;
    private Player player;


    /**
     * Konštruktor - Vykreslenie všetkych tlačidiel, textov, obrazkov na JPanel
     * @param frame - na vytvorenie noveho JPanela
     * @param player - na udržanie toho isteho playera počas behu programu
     */
    public MainMenu(JFrame frame, Player player) {
        this.player = player;
        this.mainFrame = frame;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(768, 576));
        this.setBackground(new Color(192, 192, 192));

        this.levelsLabel = new JLabel("LEVELS");
        this.levelsLabel.setBounds(335, 350, 400, 70);
        this.levelsLabel.setFont(new Font("Arial", Font.BOLD, 20));

        this.titleLabel = new JLabel("SHOOTING GAME");
        this.titleLabel.setBounds(200, 50, 400, 70);
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 36));

        this.goldLabel = new JLabel("GOLD: " + this.player.getGold());
        this.goldLabel.setBounds(650, 0, 400, 70);
        this.goldLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.goldLabel.setForeground(Color.YELLOW);

        this.buttonShop = new JButton();
        this.buttonShop.setBounds(650, 300, 70, 70);
        this.buttonShop.setEnabled(true);
        this.buttonShop.addActionListener(this.shopHandler());
        this.buttonShop.setVisible(true);
        this.buttonShop.setIcon(new ImageIcon("./pics/icons/shop.png"));

        this.lvl1 = new JButton();
        this.lvl1.setBounds(150, 400, 50, 50);
        this.lvl1.setIcon(new ImageIcon("./pics/icons/one.png"));
        this.lvl1.setEnabled(true);
        this.lvl1.addActionListener(this.mapForestHandler());
        this.lvl1.setVisible(true);

        this.lvl2 = new JButton();
        this.lvl2.setBounds(250, 400, 50, 50);
        this.lvl2.setIcon(new ImageIcon("./pics/icons/two.png"));
        this.lvl2.addActionListener(this.mapDessertHandler());
        this.lvl2.setEnabled(true);
        this.lvl2.setVisible(true);

        this.lvl3 = new JButton();
        this.lvl3.setBounds(350, 400, 50, 50);
        this.lvl3.setIcon(new ImageIcon("./pics/icons/three.png"));
        this.lvl3.addActionListener(this.mapSnowHandler());
        this.lvl3.setEnabled(true);
        this.lvl3.setVisible(true);

        this.lvl4 = new JButton();
        this.lvl4.setBounds(450, 400, 50, 50);
        this.lvl4.setIcon(new ImageIcon("./pics/icons/four.png"));
        this.lvl4.addActionListener(this.mapFieldHandler());
        this.lvl4.setEnabled(true);
        this.lvl4.setVisible(true);

        this.lvl5 = new JButton();
        this.lvl5.setBounds(550, 400, 50, 50);
        this.lvl5.setIcon(new ImageIcon("./pics/icons/five.png"));
        this.lvl5.addActionListener(this.mapBrickHanlder());
        this.lvl5.setEnabled(true);
        this.lvl5.setVisible(true);

        this.add(this.buttonShop);
        this.add(this.titleLabel);
        this.add(this.levelsLabel);
        this.add(this.goldLabel);
        this.add(this.lvl1);
        this.add(this.lvl2);
        this.add(this.lvl3);
        this.add(this.lvl4);
        this.add(this.lvl5);
    }

    // Presun na iný JPanel
    private ActionListener shopHandler() {

        /**
         * metóda pomocou ktorej sa presuvame na iny JPanel (Shop)
         * @param e event spusteny stlačením tlačitka
         */
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame window = new JFrame();
                Shop shop = new Shop(window, MainMenu.this.player);

                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setResizable(false);
                window.setTitle("Shooting game");
                window.add(shop);
                window.pack();
                window.setLocationRelativeTo(null);
                window.setVisible(true);
                MainMenu.this.mainFrame.dispose();


            }
        };
    }

    // Presun na iný JPanel
    private ActionListener mapForestHandler() {

        return new ActionListener() {

            /**
             * metóda pomocou ktorej sa presuvame na iny JPanel (MapForest)
             * @param e event spusteny stlačením tlačitka
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame window = new JFrame();
                MapForest mapForest = new MapForest(window, MainMenu.this.player);

                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setResizable(false);
                window.setTitle("Shooting game");
                window.add(mapForest);
                window.pack();
                window.setLocationRelativeTo(null);
                window.setVisible(true);
                mapForest.startGameThread();
                MainMenu.this.mainFrame.dispose();
            }
        };
    }

    // Presun na iný JPanel
    private ActionListener mapDessertHandler() {

        return new ActionListener() {

            /**
             * metóda pomocou ktorej sa presuvame na iny JPanel (MapDessert)
             * @param e event spusteny stlačením tlačitka
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame window = new JFrame();
                MapDessert mapDessert = new MapDessert(window, MainMenu.this.player);

                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setResizable(false);
                window.setTitle("Shooting game");
                window.add(mapDessert);
                window.pack();
                window.setLocationRelativeTo(null);
                window.setVisible(true);
                mapDessert.startGameThread();
                MainMenu.this.mainFrame.dispose();
            }
        };
    }

    // Presun na iný JPanel
    private ActionListener mapSnowHandler() {

        return new ActionListener() {

            /**
             * metóda pomocou ktorej sa presuvame na iny JPanel (MapSnow)
             * @param e event spusteny stlačením tlačitka
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame window = new JFrame();
                MapSnow mapSnow = new MapSnow(window, MainMenu.this.player);

                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setResizable(false);
                window.setTitle("Shooting game");
                window.add(mapSnow);
                window.pack();
                window.setLocationRelativeTo(null);
                window.setVisible(true);
                mapSnow.startGameThread();
                MainMenu.this.mainFrame.dispose();
            }
        };
    }

    // Presun na iný JPanel
    private ActionListener mapFieldHandler() {

        return new ActionListener() {
            /**
             * metóda pomocou ktorej sa presuvame na iny JPanel (MapField)
             * @param e event spusteny stlačením tlačitka
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame window = new JFrame();
                MapField mapField = new MapField(window, MainMenu.this.player);

                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setResizable(false);
                window.setTitle("Shooting game");
                window.add(mapField);
                window.pack();
                window.setLocationRelativeTo(null);
                window.setVisible(true);
                mapField.startGameThread();
                MainMenu.this.mainFrame.dispose();
            }
        };
    }

    // Presun na iný JPanel
    private ActionListener mapBrickHanlder() {

        return new ActionListener() {
            /**
             * metóda pomocou ktorej sa presuvame na iny JPanel (MapBrick)
             * @param e event spusteny stlačením tlačitka
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame window = new JFrame();
                MapBrick mapBoss = new MapBrick(window, MainMenu.this.player);

                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setResizable(false);
                window.setTitle("Shooting game");
                window.add(mapBoss);
                window.pack();
                window.setLocationRelativeTo(null);
                window.setVisible(true);
                mapBoss.startGameThread();
                MainMenu.this.mainFrame.dispose();
            }
        };
    }



}