package com.company;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {

    // Pohyb

    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //hore
        if (code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        //dole
        if (code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        //do lava
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        //do prava
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        //strielat
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        //hore
        if (code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        //dole
        if (code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        //do lava
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        //do prava
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        //strielat
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;

        }
    }
}
