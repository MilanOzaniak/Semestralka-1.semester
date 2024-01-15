package com.company.windows;

import com.company.utils.Player;

import javax.swing.JFrame;

public class Main {

    /**
     * metóda main
     * @param args
     */
    public static void main(String[] args) {
        Player player = new Player();

        JFrame frame = new JFrame("Shooting Game");
        MainMenu mainMenu = new MainMenu(frame, player);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainMenu);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
