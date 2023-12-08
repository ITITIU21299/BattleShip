package Main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.*;

//1 Ships of player
//2 Ships of computer
//0 mean empty

public class BoardGame extends JPanel implements KeyListener {
    private int number_horizontal = 10;
    private int number_vertical = 10;
    private int gridsize = 64;
    private int startX = 32;
    private int currentX = 0;  // Track current position X
    private int currentY = 0;  // Track current position Y
    public static String[][] Player_Positions;
    public static String[][] Computer_Positions;
    private Set<String> redLines = new HashSet<>();
    private static final int MAX_ENTER_COUNT = 5;
    private int enterCount = 0;

    public BoardGame() {
        Player_Positions = new String[10][10];
        Computer_Positions = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Player_Positions[i][j] = " 0 ";
                Computer_Positions[i][j] = " 0 ";
            }
        }

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        initializeComputerCrosses();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.cyan);

        drawTableTitle(g, "Your  Table", 100, 64 * 2);
        drawTableTitle(g, "Opponent Table", 1100, 64 * 2);
        drawGraphics(g,"Match",672,100);

        g.setColor(Color.blue);
        g.fillRect(32, 64 * 2, 640, 640);
        g.fillRect(864, 64 * 2, 640, 640);

        drawGrid(g);

        // Draw the selected grid darker
        g.setColor(Color.darkGray);
        g.fillRect(startX + currentX * gridsize, 64 * 2 + currentY * gridsize, gridsize, gridsize);



        g.setColor(Color.red);
        for (String position : redLines) {
            String[] parts = position.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            g.drawLine(x, y, x + gridsize, y + gridsize);
            g.drawLine(x, y + gridsize, x + gridsize, y);
        }

        g.setColor(Color.black);
        for (int i = 0; i < Computer_Positions.length; i++) {
            for (int j = 0; j < Computer_Positions[i].length; j++) {
                if (" 1 ".equals(Computer_Positions[i][j])) {
                    int x = 64 * j + 32 + 832;
                    int y = 64 * i + 128;
                    g.drawLine(x, y, x + gridsize, y + gridsize);
                    g.drawLine(x, y + gridsize, x + gridsize, y);
                }
            }
        }


    }

    void drawTableTitle(Graphics g, String title, int x, int y) {
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(title, x, y - 10);
    }
    void drawGraphics (Graphics g, String title, int x, int y) {
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(title, x, y - 10);
    }

    private void initializeComputerCrosses() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int row = random.nextInt(9);
            int column = random.nextInt(9);
            if (" 0 ".equals(Computer_Positions[row][column])) {
                Computer_Positions[row][column] = " 1 ";
            }
        }
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.cyan);

        for (int i = 0; i < number_horizontal; i++) {
            int x = startX + i * gridsize;
            g.drawLine(x, 64 * 2, x, getHeight() - 64);
        }

        for (int i = 0; i < number_horizontal; i++) {
            int x = 864 + i * gridsize;
            g.drawLine(x, 64 * 2, x, getHeight() - 64);
        }

        for (int i = 0; i < number_vertical; i++) {
            g.drawLine(32, 64 * 2 + 64 * i, 672, 64 * 2 + 64 * i);
        }

        for (int i = 0; i < number_vertical; i++) {
            g.drawLine(864, 64 * 2 + 64 * i, 1504, 64 * 2 + 64 * i);
        }


    }





    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_W:
                if (currentY > 0) {
                    currentY--;
                    repaint();
                }
                break;
            case KeyEvent.VK_S:
                if (currentY <= number_vertical - 2) {
                    currentY++;
                    repaint();
                }
                break;
            case KeyEvent.VK_A:
                if (currentX > 0) {
                    currentX--;
                    repaint();
                }
                break;
            case KeyEvent.VK_D:
                if (currentX < number_horizontal - 1) {
                    currentX++;
                    repaint();
                }
                break;
            case KeyEvent.VK_ENTER:
                performEnterAction();
                break;
        }
    }

    private void performEnterAction() {

        if (enterCount < MAX_ENTER_COUNT) {
            redLines.add(startX + currentX * gridsize + "," + (64 * 2 + currentY * gridsize));
            repaint();

             int currentGridX = 0;
             int currentGridY = 0;
            currentGridX = startX + currentX * gridsize;
            currentGridY = 64 * 2 + currentY * gridsize;
            System.out.println("The" +enterCount +" time " );

            System.out.println(" x is : " + currentGridX);
            System.out.println(" y is : "+currentGridY);
            int row=(currentGridY-128)/64;
            int column=(currentGridX-32)/64;
            System.out.println("Row is  :" + row);
            System.out.println("Column is :" +column);
            if(Player_Positions[row][column]== " 0 ") {
                Player_Positions[row][column] = " 1 ";
                enterCount++;
            }else{
                JOptionPane.showMessageDialog(this, "You have put this location already");

            }
        } else {
            JOptionPane.showMessageDialog(this, "You have reached the maximum limit of entries (5).");
        }

    }



    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Match");
        BoardGame gridPanel = new BoardGame();

        frame.add(gridPanel);
        frame.setSize(1600, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener( gridPanel);  // Make sure the panel receives key events
    }

}
