package com.company.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {

    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean spacePressed;

    /**
     * Getter
     * @return vracia hodnotu atribútu isUpPressed
     */
    public boolean isUpPressed() {
        return this.upPressed;
    }

    /**
     * Getter
     * @return vracia hodnotu atribútu isDownPressed
     */
    public boolean isDownPressed() {
        return this.downPressed;
    }

    /**
     * Getter
     * @return vracia hodnotu atribútu isLeftPressed
     */
    public boolean isLeftPressed() {
        return this.leftPressed;
    }

    /**
     * Getter
     * @return vracia hodnotu atribútu isRightPressed
     */
    public boolean isRightPressed() {
        return this.rightPressed;
    }

    /**
     * Getter
     * @return vracia hodnotu atribútu isSpacePressed
     */
    public boolean isSpacePressed() {
        return this.spacePressed;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Metoda pomocou ktorej detekujeme slačenie klavesy na klavesnici
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //hore
        if (code == KeyEvent.VK_UP) {
            this.upPressed = true;
        }
        //dole
        if (code == KeyEvent.VK_DOWN) {
            this.downPressed = true;
        }
        //do lava
        if (code == KeyEvent.VK_LEFT) {
            this.leftPressed = true;
        }
        //do prava
        if (code == KeyEvent.VK_RIGHT) {
            this.rightPressed = true;
        }
        //strielat
        if (code == KeyEvent.VK_SPACE) {
            this.spacePressed = true;
        }

    }

    /**
     * Metoda pomocou ktorej detekujeme uvolnenie klavesy na klavesnici
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        //hore
        if (code == KeyEvent.VK_UP) {
            this.upPressed = false;
        }
        //dole
        if (code == KeyEvent.VK_DOWN) {
            this.downPressed = false;
        }
        //do lava
        if (code == KeyEvent.VK_LEFT) {
            this.leftPressed = false;
        }
        //do prava
        if (code == KeyEvent.VK_RIGHT) {
            this.rightPressed = false;
        }
        //strielat
        if (code == KeyEvent.VK_SPACE) {
            this.spacePressed = false;

        }
    }
}
