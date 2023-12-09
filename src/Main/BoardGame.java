package Main;

import Inputs.MyKeyListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.*;

public class BoardGame extends JPanel  {

    public static final int GRID_SIZE = 64;
    public static final int NUMBER_HORIZONTAL = 10;
    public static final int NUMBER_VERTICAL = 10;
    public static final int START_X = 32;
    public static final int MAX_ENTER_COUNT = 5;

    public static int currentX = 0;
    public static int currentY = 0;
    private static int enterCount = 0;
    private static Set<String> redLines = new HashSet<>();

    private Player player;
    private Computer computer;
    private boolean fillRectEnabled = true;

    Battle battle;

    public BoardGame() {
        player = new Player();
        computer = new Computer();
        addKeyListener(new MyKeyListener(this));
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        initializeComputerCrosses();
        battle=new Battle(this);
    }

    @Override
    public  void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGameInfo(g);
        drawGrid(g);
        player.drawShips(g);
        drawSelectedGrid(g);
        drawRedLines(g);

        if (Player.ships < 5 && fillRectEnabled) {
            g.setColor(Color.darkGray);
            g.fillRect(START_X + currentX * GRID_SIZE, 64 * 2 + currentY * GRID_SIZE, GRID_SIZE, GRID_SIZE);
        } else if (fillRectEnabled) {
            // Clear the fill rect if it was previously filled
            g.clearRect(START_X + currentX * GRID_SIZE, 64 * 2 + currentY * GRID_SIZE, GRID_SIZE, GRID_SIZE);
            fillRectEnabled = false; // Update the flag
        }

        g.setColor(Color.black);
        for (int i = 0; i < Computer.positions.length; i++) {
            for (int j = 0; j < Computer.positions[i].length; j++) {
                if (Computer.positions[i][j]==1) {
                    int x = 64 * j + 32 + 832;
                    int y = 64 * i + 128;
                    g.drawLine(x, y, x + GRID_SIZE, y + GRID_SIZE);
                    g.drawLine(x, y + GRID_SIZE, x + GRID_SIZE, y);
                }
            }
        }
        drawBattle_Effect(g);
        draw_Computer(g);
    }

    private void drawGameInfo(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.YELLOW);
        g.drawString("Player Ships: " + player.getShips(), 100, 64);
        g.drawString("Computer Ships: " + computer.getShips(), 1100, 64);
        g.setColor(Color.yellow);
        drawTableTitle(g, "Your  Table", 100, 64 * 2);
        drawTableTitle(g, "Opponent Table", 1100, 64 * 2);


    }

    private void drawSelectedGrid(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(START_X + currentX * GRID_SIZE, 64 * 2 + currentY * GRID_SIZE, GRID_SIZE, GRID_SIZE);
    }

    private void drawRedLines(Graphics g) {
        g.setColor(Color.red);
        for (String position : redLines) {
            String[] parts = position.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            g.drawLine(x, y, x + GRID_SIZE, y + GRID_SIZE);
            g.drawLine(x, y + GRID_SIZE, x + GRID_SIZE, y);
        }
    }

    private void drawTableTitle(Graphics g, String title, int x, int y) {
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(title, x, y - 10);
    }
    private void drawGrid(Graphics g) {
        g.setColor(Color.cyan);

        for (int i = 0; i <= NUMBER_HORIZONTAL; i++) {
            int x = START_X + i * GRID_SIZE;
            g.drawLine(x, 64 * 2, x, 768);
        }

        for (int i = 0; i <= NUMBER_HORIZONTAL; i++) {
            int x = 864 + i * GRID_SIZE;
            g.drawLine(x, 64 * 2, x, 768);
        }

        for (int i = 0; i <= NUMBER_VERTICAL; i++) {
            g.drawLine(32, 64 * 2 + 64 * i, 672, 64 * 2 + 64 * i);
        }

        for (int i = 0; i <= NUMBER_VERTICAL; i++) {
            g.drawLine(864, 64 * 2 + 64 * i, 1504, 64 * 2 + 64 * i);
        }
    }
    public void initializeComputerCrosses() {
        Random random = new Random();
        Set<String> usedPositions = new HashSet<>();

        for (int i = 0; i < 5; i++) {
            int row, column;
            String position;

            do {
                row = random.nextInt(9);
                column = random.nextInt(9);
                position = row + "," + column;
            } while (usedPositions.contains(position));

            usedPositions.add(position);

            if (Computer.positions[row][column] == 0) {
                Computer.positions[row][column] = 1;
            }
        }
    }

    public  void performEnterAction() {
        if (enterCount < MAX_ENTER_COUNT) {
            redLines.add(START_X + currentX * GRID_SIZE + "," + (64 * 2 + currentY * GRID_SIZE));
            repaint();
            int currentGridX = 0;
            int currentGridY = 0;
            currentGridX = START_X + currentX * GRID_SIZE;
            currentGridY = 64 * 2 + currentY * GRID_SIZE;
            int row=(currentGridY-128)/64;
            int column=(currentGridX-32)/64;
            if(Player.positions[row][column]==  0 ) {
                Player.positions[row][column] =  1 ;
                enterCount++;
                Player.ships++;
            }else{
                JOptionPane.showMessageDialog(this, "You have put this location already");
            }
        } else {
            JOptionPane.showMessageDialog(this, "You have reached the maximum ships let enter 1 to start the battle ");
        }
    }

    public void drawBattle_Effect(Graphics g){
        g.setColor(Color.YELLOW);
        for (String position : Battle.Oval_player) {
            String[] parts = position.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            g.fillOval(x,y,64,64);
        }


    }
    public void draw_Computer(Graphics g){
        g.setColor(Color.MAGENTA);
        for (String position : Battle.Oval_Computer) {
            String[] parts = position.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            g.fillOval(x,y,64,64);
        }
    }
    public void startGame() {
        if (Player.ships < 5) {
            JOptionPane.showMessageDialog(null, "You need to have 5 ships for the battle");
        } else {
            battle.fight();
        }
    }



}

