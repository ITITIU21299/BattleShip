package Main;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

class Player {
    private static final int MAX_SHIPS = 5;
    private static final int EMPTY_POSITION =  0 ;
    private static final int OCCUPIED_POSITION =  1 ;
    public Set<String> Submarine = new HashSet<>();
    public  Set<String> BattleShip = new HashSet<>();
    public  Set<String> Cruiser = new HashSet<>();
    public  Set<String> carrier = new HashSet<>();
    public  Set<String> Destroyer = new HashSet<>();
    int sunk=2;

    public static int[][] positions;
    public  static int ships;

    public Player() {
        ships = 0;
        positions = new int[BoardGame.NUMBER_HORIZONTAL][BoardGame.NUMBER_VERTICAL];
        for (int i = 0; i < BoardGame.NUMBER_HORIZONTAL; i++) {
            for (int j = 0; j < BoardGame.NUMBER_VERTICAL; j++) {
                positions[i][j] = EMPTY_POSITION;
            }
        }
    }

    public void SubMarine(int row, int column) {
    boolean skip=true;
            while (skip) {
                if(row!=9&column!=0&&column!=9){
                    break;
                }else {
                    try {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please re-enter.");
                        String input_y = JOptionPane.showInputDialog("Enter row axis:");
                        if (input_y == null ) {
                            JOptionPane.showMessageDialog(null, "Input canceled. Exiting the game.");
                            System.exit(0);
                        }
                        row = Integer.parseInt(input_y);

                        String input_x = JOptionPane.showInputDialog("Enter column axis:");
                        if (input_x == null ) {
                            JOptionPane.showMessageDialog(null, "Input canceled. Exiting the game.");
                            System.exit(0);
                        }
                        column = Integer.parseInt(input_x);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid input format. Please enter numeric values.");
                    }
                }
            }
        boolean skips=true;
        while (skips) {
            if(positions[row][column] ==0 &&
            positions[row + 1][column] ==0&&
            positions[row + 1][column + 1] == 0&&
            positions[row + 1][column - 1] ==0 ){
                break;
            }else {
                try {
                    JOptionPane.showMessageDialog(null, "Already put ship here. Please re-enter.");
                    String input_y = JOptionPane.showInputDialog("Enter row axis:");
                    if (input_y == null ) {
                        JOptionPane.showMessageDialog(null, "Input canceled. Exiting the game.");
                        System.exit(0);
                    }
                    row = Integer.parseInt(input_y);

                    String input_x = JOptionPane.showInputDialog("Enter column axis:");
                    if (input_x == null ) {
                        JOptionPane.showMessageDialog(null, "Input canceled. Exiting the game.");
                        System.exit(0);
                    }
                    column = Integer.parseInt(input_x);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input format. Please enter numeric values.");
                }
            }
        }
        ships++;
        positions[row][column] = 1;
        positions[row + 1][column] = 1;
        positions[row + 1][column + 1] = 1;
        positions[row + 1][column - 1] = 1;

        int x1 = 64 * column + 32;
        int x2 = 64 * (column + 1) + 32;
        int x3 = 64 * (column - 1) + 32;
        int y1 = 64 * row + 128;
        int y2 = 64 * (row + 1) + 128;

        Submarine.add(x1 + "," + y1);
        Submarine.add(x1 + "," + y2);
        Submarine.add(x2 + "," + y2);
        Submarine.add(x3 + "," + y2);
        }




    public  void BattleShip(int row,int column){
        boolean skip=true;
        while (skip) {
            if(row!=9&column!=0&&column!=9&&column<8){
                break;
            }else {
                try {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please re-enter.");
                    String input_y = JOptionPane.showInputDialog("Enter row axis:");
                    if (input_y == null ) {
                        JOptionPane.showMessageDialog(null, "Input canceled. Exiting the game.");
                        System.exit(0);
                    }
                    row = Integer.parseInt(input_y);

                    String input_x = JOptionPane.showInputDialog("Enter column axis:");
                    if (input_x == null ) {
                        JOptionPane.showMessageDialog(null, "Input canceled. Exiting the game.");
                        System.exit(0);
                    }
                    column = Integer.parseInt(input_x);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input format. Please enter numeric values.");
                }
            }
        }

        boolean skips=true;
        while (skips) {
            if( positions[row][column] == 0&&
            positions[row ][column+1] == 0&&
            positions[row ][column + 2] == 0&&
            positions[row + 1][column - 1] == 0&&
            positions[row +1 ][column] == 0 &&
            positions[row +1][column+1] == 0 ){
                break;
            }else {
                try {
                    JOptionPane.showMessageDialog(null, "Already put ship here. Please re-enter.");
                    String input_y = JOptionPane.showInputDialog("Enter row axis:");
                    if (input_y == null ) {
                        JOptionPane.showMessageDialog(null, "Input canceled. Exiting the game.");
                        System.exit(0);
                    }
                    row = Integer.parseInt(input_y);

                    String input_x = JOptionPane.showInputDialog("Enter column axis:");
                    if (input_x == null ) {
                        JOptionPane.showMessageDialog(null, "Input canceled. Exiting the game.");
                        System.exit(0);
                    }
                    column = Integer.parseInt(input_x);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input format. Please enter numeric values.");
                }
            }
        }
        ships++;


        positions[row][column] = 1;
        positions[row ][column+1] = 1;
        positions[row ][column + 2] = 1;
        positions[row + 1][column - 1] = 1;
        positions[row +1 ][column] = 1;
        positions[row +1][column+1] = 1;
        int x1,y1,x2,y2,x3,y3,x4,y4;
        x1=64*column+32; //column
        x2=64*(column+1)+32;//column1
        x3=64*(column+2)+32;//column2
        x4=64*(column-1)+32;//column-1
        y1=64*row+128;//row
        y2=64*(row+1)+128;//row+1
        BattleShip.add(x1 + "," + y1);
        BattleShip.add(x2 + "," + y1);
        BattleShip.add(x3 + "," + y1);
        BattleShip.add(x4 + "," + y2);
        BattleShip.add(x1 + "," + y2);
        BattleShip.add(x2 + "," + y2);

        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                System.out.printf(" %d ",positions[i][j]);
            }
            System.out.println();
        }
    }

    public  void Destroyer(int row,int column){
        boolean skip=true;
        while (skip) {
            if(row<9){
                break;
            }else {
                try {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please re-enter.");
                    String input_y = JOptionPane.showInputDialog("Enter row axis:");
                    if (input_y == null ) {
                        JOptionPane.showMessageDialog(null, "Input canceled. Exiting the game.");
                        System.exit(0);
                    }
                    row = Integer.parseInt(input_y);

                    String input_x = JOptionPane.showInputDialog("Enter column axis:");
                    if (input_x == null ) {
                        JOptionPane.showMessageDialog(null, "Input canceled. Exiting the game.");
                        System.exit(0);
                    }
                    column = Integer.parseInt(input_x);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input format. Please enter numeric values.");
                }
            }
        }

        boolean skips=true;
        while (skips) {
            if(positions[row][column] ==0&&positions[row+1][column] == 0 ){
                break;
            }else {
                try {
                    JOptionPane.showMessageDialog(null, "Already put ship here. Please re-enter.");
                    String input_y = JOptionPane.showInputDialog("Enter row axis:");
                    if (input_y == null ) {
                        JOptionPane.showMessageDialog(null, "Input canceled. Exiting the game.");
                        System.exit(0);
                    }
                    row = Integer.parseInt(input_y);

                    String input_x = JOptionPane.showInputDialog("Enter column axis:");
                    if (input_x == null ) {
                        JOptionPane.showMessageDialog(null, "Input canceled. Exiting the game.");
                        System.exit(0);
                    }
                    column = Integer.parseInt(input_x);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input format. Please enter numeric values.");
                }
            }
        }
        ships++;
        positions[row][column] = 1;
        positions[row+1][column] = 1;
        int x1,y1,x2,y2,x3,y3,x4,y4;
        x1=64*column+32; //column
        y1=64*row+128;//row
        y2=64*(row+1)+128;//row+1
        Destroyer.add(x1 + "," + y1);
        Destroyer.add(x1 + "," + y2);

        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                System.out.printf(" %d ",positions[i][j]);
            }
            System.out.println();
        }
    }
    public   void Carrier(int row,int column){
        boolean skip=true;
        while (skip) {
            if(row<6){
                break;
            }else {
                try {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please re-enter.");
                    String input_y = JOptionPane.showInputDialog("Enter row axis:");
                    if (input_y == null ) {
                        JOptionPane.showMessageDialog(null, "Input canceled. Exiting the game.");
                        System.exit(0);
                    }
                    row = Integer.parseInt(input_y);

                    String input_x = JOptionPane.showInputDialog("Enter column axis:");
                    if (input_x == null ) {
                        JOptionPane.showMessageDialog(null, "Input canceled. Exiting the game.");
                        System.exit(0);
                    }
                    column = Integer.parseInt(input_x);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input format. Please enter numeric values.");
                }
            }
        }


        boolean skips=true;
        while (skips) {
            if(positions[row][column] == 0&& positions[row+1][column] == 0&& positions[row+2][column] ==0&& positions[row+3][column] == 0&& positions[row+4][column] ==0 ){
                break;
            }else {
                try {
                    JOptionPane.showMessageDialog(null, "Already put ship here. Please re-enter.");
                    String input_y = JOptionPane.showInputDialog("Enter row axis:");
                    if (input_y == null ) {
                        JOptionPane.showMessageDialog(null, "Input canceled. Exiting the game.");
                        System.exit(0);
                    }
                    row = Integer.parseInt(input_y);

                    String input_x = JOptionPane.showInputDialog("Enter column axis:");
                    if (input_x == null ) {
                        JOptionPane.showMessageDialog(null, "Input canceled. Exiting the game.");
                        System.exit(0);
                    }
                    column = Integer.parseInt(input_x);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input format. Please enter numeric values.");
                }
            }
        }
        ships++;

        positions[row][column] = 1;
        positions[row+1][column] = 1;
        positions[row+2][column] = 1;
        positions[row+3][column] = 1;
        positions[row+4][column] = 1;

        int x1,y1,x2,y2,x3,y3,x4,y4,y5;
        x1=64*column+32; //column

        y1=64*row+128;//row
        y2=64*(row+1)+128;//row+1
        y3=64*(row+2)+128;
        y4=64*(row+3) +128;
        y5=64*(row+4) +128;
        carrier.add(x1 + "," + y1);
        carrier.add(x1 + "," + y2);
        carrier.add(x1 + "," + y3);
        carrier.add(x1 + "," + y4);
        carrier.add(x1 + "," + y5);

        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                System.out.printf(" %d ",positions[i][j]);
            }
            System.out.println();
        }
    }
    public  void Cruiser(int row,int column){
        boolean skip=true;
        while (skip) {
            if(column<6){
                break;
            }else {
                try {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please re-enter.");
                    String input_y = JOptionPane.showInputDialog("Enter row axis:");
                    if (input_y == null ) {
                        JOptionPane.showMessageDialog(null, "Input canceled. Exiting the game.");
                        System.exit(0);
                    }
                    row = Integer.parseInt(input_y);

                    String input_x = JOptionPane.showInputDialog("Enter column axis:");
                    if (input_x == null ) {
                        JOptionPane.showMessageDialog(null, "Input canceled. Exiting the game.");
                        System.exit(0);
                    }
                    column = Integer.parseInt(input_x);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input format. Please enter numeric values.");
                }
            }
        }

        boolean skips=true;
        while (skips) {
            if( positions[row][column] == 0&&
            positions[row][column+1] ==0&&
            positions[row][column+2] ==0&&
            positions[row][column+3] == 0&&
            positions[row][column+4] == 0 ){
                break;
            }else {
                try {
                    JOptionPane.showMessageDialog(null, "Already put ship here. Please re-enter.");
                    String input_y = JOptionPane.showInputDialog("Enter row axis:");
                    if (input_y == null ) {
                        JOptionPane.showMessageDialog(null, "Input canceled. Exiting the game.");
                        System.exit(0);
                    }
                    row = Integer.parseInt(input_y);

                    String input_x = JOptionPane.showInputDialog("Enter column axis:");
                    if (input_x == null ) {
                        JOptionPane.showMessageDialog(null, "Input canceled. Exiting the game.");
                        System.exit(0);
                    }
                    column = Integer.parseInt(input_x);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input format. Please enter numeric values.");
                }
            }
        }
        ships++;
        positions[row][column] = 1;
        positions[row][column+1] = 1;
        positions[row][column+2] = 1;
        positions[row][column+3] = 1;
        positions[row][column+4] = 1;

        int x1,y1,x2,y2,x3,y3,x4,y4,x5;
        x1=64*column+32; //column
        x2=64*(column+1)+32;
        x3=64*(column+2)+32;
        x4=64*(column+3)+32;
        x5=64*(column+4)+32;
        y1=64*row+128;//row
        Cruiser.add(x1 + "," + y1);
        Cruiser.add(x2 + "," + y1);
        Cruiser.add(x3 + "," + y1);
        Cruiser.add(x4 + "," + y1);
        Cruiser.add(x5 + "," + y1);

        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                System.out.printf(" %d ",positions[i][j]);
            }
            System.out.println();
        }
    }



}


