package Main;

import javax.swing.*;
import java.util.Random;

import static Main.Player.*;

public class BattleShip extends Ship{
    @Override
    public void make_ship_player(int row, int column) {
        super.make_ship_player( row, column);
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
        Point point1=new Point(row,column);
        point1.setZ(3);
        points.add(point1);
        Point point2=new Point(row,column+1);
        point2.setZ(3);
        points.add(point2);
        Point point3=new Point(row,column+2);
        point3.setZ(3);
        points.add(point3);
        Point point4=new Point(row+1,column-1);
        point4.setZ(3);
        points.add(point4);
        Point point5=new Point(row+1,column);
        point5.setZ(3);
        points.add(point5);
        Point point6=new Point(row+1,column+1);
        point6.setZ(3);
        points.add(point6);


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
    }

    @Override
    public void make_ship_computer() {
        super.make_ship_computer();
        boolean check1=false;
        boolean check2=false;
        int row ,column;
        Random random=new Random();
        while(true){
            row = random.nextInt(9);
            column = random.nextInt(8);
            if(row!=9&column!=0&&column!=9&&column<8){
                check1=true;
            }
            if(check1==false){
                continue;
            }
            if( Computer.positions[row][column] == 0&&
                    Computer.positions[row ][column+1] == 0&&
                    Computer.positions[row ][column + 2] == 0&&
                    Computer.positions[row + 1][column - 1] == 0&&
                    Computer.positions[row +1 ][column] == 0 &&
                    Computer.positions[row +1][column+1] == 0 ){
                check2=true;
            }

            if(check1==true&&check2==true){
                break;
            }

        }
        Computer.positions[row][column] = 1;
        Computer.positions[row ][column+1] = 1;
        Computer.positions[row ][column + 2] = 1;
        Computer.positions[row + 1][column - 1] = 1;
        Computer.positions[row +1 ][column] = 1;
        Computer.positions[row +1][column+1] = 1;
        System.out.println("BattleShip is ");
        Point point1=new Point(row,column);
        point1.setZ(3);
        Computer.points.add(point1);
        Point point2=new Point(row,column+1);
        point2.setZ(3);
        Computer.points.add(point2);
        Point point3=new Point(row,column+2);
        point3.setZ(3);
        Computer.points.add(point3);
        Point point4=new Point(row+1,column-1);
        point4.setZ(3);
        Computer.points.add(point4);
        Point point5=new Point(row+1,column);
        point5.setZ(3);
        Computer.points.add(point5);
        Point point6=new Point(row+1,column+1);
        point6.setZ(3);
        Computer.points.add(point6);
        for (int i = 0; i < BoardGame.NUMBER_HORIZONTAL; i++) {
            for (int j = 0; j < BoardGame.NUMBER_VERTICAL; j++) {
                System.out.printf(" %d ",Computer.positions[i][j]);
            }
            System.out.println();
        }
    }
}
