package Inputs;

import Main.BoardGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {
    private BoardGame boardGame;  // Reference to the main class

    public MyKeyListener(BoardGame boardGame) {
        this.boardGame = boardGame;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_W:
                if (boardGame.currentY > 0) {
                    boardGame.currentY--;
                    boardGame.repaint();
                }
                break;
            case KeyEvent.VK_S:
                if (boardGame.currentY <= boardGame.NUMBER_HORIZONTAL - 2) {
                    boardGame.currentY++;
                    boardGame.repaint();
                }

                break;

            case KeyEvent.VK_A:
                if (BoardGame.currentX > 0) {
                    BoardGame.currentX--;
                    boardGame.repaint();
                }
                break;
            case KeyEvent.VK_D:
                if (BoardGame.currentX < boardGame.NUMBER_HORIZONTAL - 1) {
                    BoardGame.currentX++;
                    boardGame.repaint();
                }
                break;
            case KeyEvent.VK_ENTER:
                boardGame.performEnterAction();
                break;
            case KeyEvent.VK_1:
                boardGame.startGame();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Handle key typed event if needed
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Handle key released event if needed
    }
}