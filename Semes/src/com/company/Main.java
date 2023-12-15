package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        //JPanel window = new JPanel();
        GamePanel gamePanel = new GamePanel();

        //window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setResizable(false);
        //window.setTitle("SHOOTING GAME");
        //window.add(gamePanel);
        //window.pack();
        //window.setLocationRelativeTo(null);
        //window.setVisible(true);
        new MainMenu();
        gamePanel.startGameThread();
    }
}
