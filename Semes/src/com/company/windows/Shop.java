package com.company.windows;

import com.company.utils.Player;
import com.company.utils.Weapon;

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


public class Shop extends JPanel {

    private JFrame mainFrame;
    private JLabel titleLabel;
    private JLabel price;
    private JLabel price1;
    private JLabel price2;
    private JLabel damage;
    private JLabel damage1;
    private JLabel damage2;
    private JLabel atSpeed;
    private JLabel atSpeed1;
    private JLabel atSpeed2;
    private JLabel warning;
    private JButton buttonAk;
    private JButton buttonHeavyAr;
    private JButton buttonLaserGun;
    private JButton buttonBack;
    private JLabel picAk;
    private JLabel goldLabel;
    private JLabel picHeavyAr;
    private JLabel picLaserGun;
    private Player player;


    /**
     * Konštruktor - Vykreslenie všetkych tlačidiel, textov, obrazkov na JPanel
     * @param frame - na vytvorenie noveho JPanela
     * @param player - na udržanie toho isteho playera počas behu programu
      */
    public Shop(JFrame frame, Player player) {
        this.player = player;
        this.mainFrame = frame;



        this.setBackground(new Color(192, 192, 192));
        this.setLayout(null);
        this.setPreferredSize(new Dimension(768, 576));

        this.titleLabel = new JLabel("SHOP");
        this.titleLabel.setBounds(300, 50, 400, 70);
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 36));

        this.goldLabel = new JLabel("GOLD: " + this.player.getGold());
        this.goldLabel.setBounds(650, 0, 400, 70);
        this.goldLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.goldLabel.setForeground(Color.YELLOW);

        this.price = new JLabel("PRICE : 250 GOLD");
        this.price.setBounds(100, 350, 400, 70);
        this.price.setFont(new Font("Arial", Font.BOLD, 15));

        this.price1 = new JLabel("PRICE : 400 GOLD");
        this.price1.setBounds(300, 350, 400, 70);
        this.price1.setFont(new Font("Arial", Font.BOLD, 15));

        this.price2 = new JLabel("PRICE : 650 GOLD");
        this.price2.setBounds(500, 350, 400, 70);
        this.price2.setFont(new Font("Arial", Font.BOLD, 15));

        this.damage = new JLabel("DAMAGE : 50");
        this.damage.setBounds(100, 370, 400, 70);
        this.damage.setFont(new Font("Arial", Font.BOLD, 15));

        this.damage1 = new JLabel("DAMAGE : 75");
        this.damage1.setBounds(300, 370, 400, 70);
        this.damage1.setFont(new Font("Arial", Font.BOLD, 15));

        this.damage2 = new JLabel("DAMAGE : 30");
        this.damage2.setBounds(500, 370, 400, 70);
        this.damage2.setFont(new Font("Arial", Font.BOLD, 15));

        this.atSpeed = new JLabel("ATTK SPD : 1");
        this.atSpeed.setBounds(100, 390, 400, 70);
        this.atSpeed.setFont(new Font("Arial", Font.BOLD, 15));

        this.atSpeed1 = new JLabel("ATTK SPD : 0.5");
        this.atSpeed1.setBounds(300, 390, 400, 70);
        this.atSpeed1.setFont(new Font("Arial", Font.BOLD, 15));

        this.atSpeed2 = new JLabel("ATTK SPD : 2.5");
        this.atSpeed2.setBounds(500, 390, 400, 70);
        this.atSpeed2.setFont(new Font("Arial", Font.BOLD, 15));

        this.warning = new JLabel("");
        this.warning.setBounds(300, 450, 400, 70);
        this.warning.setFont(new Font("Arial", Font.BOLD, 15));
        this.warning.setForeground(Color.RED);

        this.buttonAk = new JButton("Buy AK-47");
        this.buttonAk.setBounds(100, 330, 150, 40);
        this.buttonAk.setEnabled(true);
        this.buttonAk.addActionListener(this.buyAkHandler());

        this.picAk = new JLabel();
        this.picAk.setIcon(new ImageIcon("./pics/icons/ak47.png"));
        Dimension size = this.picAk.getPreferredSize();
        this.picAk.setBounds(85, 200, size.width, size.height);
        this.picAk.setVisible(true);

        this.buttonHeavyAr = new JButton("Buy Heavy AR");
        this.buttonHeavyAr.setBounds(300, 330, 150, 40);
        this.buttonHeavyAr.setEnabled(true);
        this.buttonHeavyAr.addActionListener(this.buyHeavyArHandler());

        this.picHeavyAr = new JLabel();
        this.picHeavyAr.setIcon(new ImageIcon("./pics/icons/heavy_ar.png"));
        Dimension size1 = this.picHeavyAr.getPreferredSize();
        this.picHeavyAr.setBounds(285, 200, size1.width, size1.height);
        this.picHeavyAr.setVisible(true);


        this.buttonLaserGun = new JButton("Buy Laser Gun");
        this.buttonLaserGun.setBounds(500, 330, 150, 40);
        this.buttonLaserGun.setEnabled(true);
        this.buttonLaserGun.addActionListener(this.buyLaserGunHandler());

        this.picLaserGun = new JLabel();
        this.picLaserGun.setIcon(new ImageIcon("./pics/icons/laser_gun.png"));
        Dimension size2 = this.picLaserGun.getPreferredSize();
        this.picLaserGun.setBounds(485, 200, size2.width, size2.height);
        this.picLaserGun.setVisible(true);




        this.buttonBack = new JButton();
        this.buttonBack.setIcon(new ImageIcon("./pics/icons/back.png"));
        this.buttonBack.setBorder(null);
        this.buttonBack.setContentAreaFilled(false);
        this.buttonBack.setBounds(20, 20, this.buttonBack.getIcon().getIconWidth(), this.buttonBack.getIcon().getIconHeight());
        this.buttonBack.addActionListener(this.returnToMenuHandler());

        this.add(this.titleLabel);
        this.add(this.buttonAk);
        this.add(this.goldLabel);
        this.add(this.picAk);
        this.add(this.buttonHeavyAr);
        this.add(this.picHeavyAr);
        this.add(this.buttonLaserGun);
        this.add(this.picLaserGun);
        this.add(this.buttonBack);
        this.add(this.price);
        this.add(this.price1);
        this.add(this.price2);
        this.add(this.damage);
        this.add(this.damage1);
        this.add(this.damage2);
        this.add(this.atSpeed);
        this.add(this.atSpeed1);
        this.add(this.atSpeed2);
        this.add(this.warning);
    }

    // Presun na iný JPanel
    private ActionListener returnToMenuHandler() {

        return new ActionListener() {

            /**
             * metóda pomocou ktorej sa presuvame na iny JPanel (MainMenu)
             * @param e event spusteny stlačením tlačitka
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame window = new JFrame();
                MainMenu mainMenu = new MainMenu(window, Shop.this.player);

                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setResizable(false);
                window.setTitle("Shooting game");
                window.add(mainMenu);
                window.pack();
                window.setLocationRelativeTo(null);
                window.setVisible(true);
                Shop.this.mainFrame.dispose();


            }
        };
    }

    // nastavenie playerovy inu weapon
    private ActionListener buyAkHandler() {


        return new ActionListener() {

            /**
             * metóda pomocou ktorej nastavujeme hráčovi inu zbraň (AK-47)
             * @param e event spusteny stlačením tlačitka
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Shop.this.player.getGold() >= 250) {
                    Shop.this.player.setWeapon(new Weapon(50, 900, Color.BLACK));
                    Shop.this.player.setPlayerImage(new ImageIcon("./pics/player/player_ak.png").getImage());
                    Shop.this.player.setGold(Shop.this.player.getGold() - 250);
                } else {
                    Shop.this.warning.setText("NOT ENOUGH GOLD");
                }

            }
        };
    }

    // nastavenie playerovy inu weapon
    private ActionListener buyHeavyArHandler() {

        return new ActionListener() {

            /**
             * metóda pomocou ktorej nastavujeme hráčovi inu zbraň (Heavy Assault Rifle)
             * @param e event spusteny stlačením tlačitka
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Shop.this.player.getGold() >= 400) {
                    Shop.this.player.setWeapon(new Weapon(75, 1500, Color.BLACK));
                    Shop.this.player.setPlayerImage(new ImageIcon("./pics/player/player_heavyar.png").getImage());
                    Shop.this.player.setGold(Shop.this.player.getGold() - 400);
                } else {
                    Shop.this.warning.setText("NOT ENOUGH GOLD");
                }
            }
        };
    }

    // nastavenie playerovy inu weapon
    private ActionListener buyLaserGunHandler() {

        return new ActionListener() {

            /**
             * metóda pomocou ktorej nastavujeme hráčovi inu zbraň (Laser Gun)
             * @param e event spusteny stlačením tlačitka
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Shop.this.player.getGold() >= 650) {
                    Shop.this.player.setWeapon(new Weapon(30, 400, Color.CYAN));
                    Shop.this.player.setPlayerImage(new ImageIcon("./pics/player/player_laser_gun.png").getImage());
                    Shop.this.player.setGold(Shop.this.player.getGold() - 650);
                } else {
                    Shop.this.warning.setText("NOT ENOUGH GOLD");
                }
            }
        };
    }



}