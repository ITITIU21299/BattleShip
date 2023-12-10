package Main;

import Inputs.MyKeyListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
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

    Ship ship;

    Battle battle;

    public BoardGame() {
        player = new Player();
        computer = new Computer();
        addKeyListener(new MyKeyListener(this));
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        battle=new Battle(this);

    }

    @Override
    public  void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGameInfo(g);
        drawGrid(g);
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Player Ships: " + player.ships, 100, 64+16);
        g.drawString("Computer Ships: " + computer.ships, 1100, 64+16);

        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Hint: ", 700, 30);
        g.drawString("Enter for setup game ", 700, 50);
        g.drawString(" 1     to  start game ", 700, 70);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString("Type 1 is  Submarine: ", 700, 90);
        g.drawString("Type 2 is  Destroyer ", 700, 110);
        g.drawString("Type 3 is  BattleShip ", 700, 130);
        g.drawString("Type 4 is  Carrier ", 700, 150);
        g.drawString("Type 5 is  Cruiser", 700, 170);

        drawRedLines(g);
        draw_Submarine(g);
        draw_Battleship(g);
        draw_Carrier(g);
        Cruiser(g);
        Destroyer(g);
        drawBattle_Effect(g);
        draw_Computer(g);
    }

    private void drawGameInfo(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.YELLOW);
        g.setColor(Color.yellow);
        drawTableTitle(g, "Your  Table", 100, 64  );
        drawTableTitle(g, "Opponent Table", 1100, 64 );
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
        String row[]={"0","1","2","3","4","5","6","7","8","9"};
        String column[]={"0","1","2","3","4","5","6","7","8","9"};
        g.setColor(Color.cyan);

        for (int i = 0; i <= NUMBER_HORIZONTAL; i++) {
            int x = START_X + i * GRID_SIZE;
            g.drawLine(x, 64 * 2, x, 768);
        }
        for(int i=0;i<10;i++){
            int x = START_X + i * GRID_SIZE+16;
            g.setFont(new Font("Arial", Font.BOLD, 28));
            g.drawString(column[i], x, 128);
        }
        for(int i=0;i<10;i++){
            int x = 864 + i * GRID_SIZE+16;
            g.setFont(new Font("Arial", Font.BOLD, 28));
            g.drawString(column[i], x, 128);
        }

        for (int i = 0; i <= NUMBER_HORIZONTAL; i++) {
            int x = 864 + i * GRID_SIZE;
            g.drawLine(x, 64 * 2, x, 768);
        }
        for (int i = 0; i <= NUMBER_VERTICAL; i++) {
            g.drawLine(32, 64 * 2 + 64 * i, 672, 64 * 2 + 64 * i);
        }
        for(int i=0;i<10;i++){
            int x = START_X + i * GRID_SIZE+16;
            g.setFont(new Font("Arial", Font.BOLD, 28));
            g.drawString(row[i], 1, 64 * 2 + 64 * i+55);
        }
        for(int i=0;i<10;i++){
            int x = START_X + i * GRID_SIZE+16;
            g.setFont(new Font("Arial", Font.BOLD, 28));
            g.drawString(row[i], 864-20, 64 * 2 + 64 * i+55);
        }



        for (int i = 0; i <= NUMBER_VERTICAL; i++) {
            g.drawLine(864, 64 * 2 + 64 * i, 1504, 64 * 2 + 64 * i);
        }
    }


    public void drawBattle_Effect(Graphics g){
        for (String position : battle.Oval_player) {
            String[] parts = position.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            int column = (x - 864) / 64;
            int row = (y - 128) / 64;
            Color originalColor = g.getColor();

            g.fillOval(x,y,64,64);
            if (computer.positions[row][column] !=0) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.YELLOW);
            }

            g.setColor(originalColor);

        }
    }
    public void draw_Computer(Graphics g){
        for (String position : battle.Oval_Computer) {
            String[] parts = position.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            int column = (x - 32) / 64;
            int row = (y - 128) / 64;

            Color originalColor = g.getColor(); // Store the original color

            if (player.positions[row][column] !=0) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.MAGENTA);
            }

            g.fillOval(x, y, 64, 64);

            // Reset the color to the original color
            g.setColor(originalColor);
        }
    }
    public void draw_Submarine(Graphics g){
        g.setColor(Color.MAGENTA);
        for (String position : player.Submarine) {
            String[] parts = position.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            g.fillOval(x,y,64,64);
        }
    }
    public void draw_Battleship(Graphics g){
        g.setColor(Color.BLACK);
        for (String position : player.BattleShip) {
            String[] parts = position.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            g.fillOval(x,y,64,64);
        }
    }
    public void draw_Carrier(Graphics g){
        g.setColor(Color.CYAN);
        for (String position : player.carrier) {
            String[] parts = position.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            g.fillOval(x,y,64,64);
        }
    }
    public void Cruiser(Graphics g){
        g.setColor(Color.YELLOW);
        for (String position : player.Cruiser) {
            String[] parts = position.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            g.fillOval(x,y,64,64);
        }
    }
    public void Destroyer(Graphics g){
        g.setColor(Color.GREEN);
        for (String position : player.Destroyer) {
            String[] parts = position.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            g.fillOval(x,y,64,64);
        }
    }

    public void setUp() {
        if(player.ships<5) {
        JOptionPane.showMessageDialog(this, "Let's start by setting up the game ");
            ArrayList<Integer>checkType=new ArrayList<>();

    for (int i = 1; i < 6; i++) {
        JOptionPane.showMessageDialog(this, "Let's set up the " + i + " ship");
boolean check=false;
        int row;
        int column;
        int type;
        while (true) {
            try {
                String input_y = JOptionPane.showInputDialog("Enter row axis:");
                if (input_y == null) {
                    JOptionPane.showMessageDialog(this, "Input canceled. Exiting the game.");
                    System.exit(0);
                }
                String input_x = JOptionPane.showInputDialog("Enter column axis:");
                if (input_x == null) {
                    JOptionPane.showMessageDialog(this, "Input canceled. Exiting the game.");
                    System.exit(0);
                }
                String input_type = JOptionPane.showInputDialog("Enter Type:");
                if (input_type == null) {
                    JOptionPane.showMessageDialog(this, "Input canceled. Exiting the game.");
                    System.exit(0);
                }
                row = Integer.parseInt(input_y);
                column = Integer.parseInt(input_x);
                type = Integer.parseInt(input_type);
                for(int t:checkType){
                    if(t==type){
                        JOptionPane.showMessageDialog(this, "You already have this ship's type");
                        check=true;
                    }
                }
                if(check){
                    continue;
                }
                // Validate input values
                if (isValidInput(row, column, type)) {
                    break;  // Exit the loop if input is valid
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please re-enter.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input format. Please enter numeric values.");
            }
        }
        checkType.add(type);

        switch (type) {
            case 1:
                ship=new SubMarine();
                ship.make_ship_player(row, column);
                repaint();
                break;
            case 2:
                ship=new Destroyer();
                ship.make_ship_player(row, column);
                repaint();
                break;
            case 3:
                ship=new BattleShip();
                ship.make_ship_player(row, column);
                repaint();
                break;
            case 4:
                ship=new Carrier();
                ship.make_ship_player(row, column);
                repaint();
                break;
            case 5:
                ship=new Cruiser();
                ship.make_ship_player(row, column);
                repaint();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid ship type. Exiting the game.");
                System.exit(0);
        }


    }
}else{
    JOptionPane.showMessageDialog(this, "Your ship_slot is full.");
}
    }

    private boolean isValidInput(int row, int column, int type) {
        // Validate if the row and column are not negative and type is within a valid range
        return row >= 0 && column >= 0 && (type >= 1 && type <= 5);
    }

    public void startGame() {
        if (player.ships < 5) {
            JOptionPane.showMessageDialog(null, "You need to have 5 ships for the battle");
        } else {
            battle.fight();
        }
    }





}

