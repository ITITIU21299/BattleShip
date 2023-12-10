package Main;

import javax.swing.*;

import java.util.Random;

import static Main.Player.points;
import static Main.Player.positions;
import static Main.Player.*;


public class Cruiser extends Ship{
    @Override
    public void make_ship_player(int row, int column) {
        super.make_ship_player(row, column);
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

        Point point1=new Point(row,column);
        point1.setZ(5);
        points.add(point1);
        Point point2=new Point(row,column+1);
        point2.setZ(5);
        points.add(point2);
        Point point3=new Point(row,column+2);
        point3.setZ(5);
        points.add(point3);
        Point point4=new Point(row,column+3);
        point4.setZ(5);
        points.add(point4);
        Point point5=new Point(row,column+4
        );
        point5.setZ(5);
        points.add(point5);
    }

    @Override
    public void make_ship_computer() {
        super.make_ship_computer();
        boolean check1=false;
        boolean check2=false;
        int row ,column;
        Random random=new Random();
        while(true) {
            row = random.nextInt(9);
            column = random.nextInt(9);
            if(column<6){
                check1=true;
            }else {
                continue;
            }
            if( Computer.positions[row][column] == 0&&
                    Computer.positions[row][column+1] ==0&&
                    Computer.positions[row][column+2] ==0&&
                    Computer.positions[row][column+3] == 0&&
                    Computer.positions[row][column+4] == 0 ){
                check2=true;
            }

            if(check1==true&&check2==true){
                break;
            }
        }
        Computer.positions[row][column] = 1;
        Computer.positions[row][column+1] = 1;
        Computer.positions[row][column+2] = 1;
        Computer.positions[row][column+3] = 1;
        Computer.positions[row][column+4] = 1;
        System.out.println("Cruiser is ");
        for (int i = 0; i < BoardGame.NUMBER_HORIZONTAL; i++) {
            for (int j = 0; j < BoardGame.NUMBER_VERTICAL; j++) {
                System.out.printf(" %d ",Computer.positions[i][j]);
            }
            System.out.println();
        }
        Point point1=new Point(row,column);
        point1.setZ(5);
        Computer.points.add(point1);
        Point point2=new Point(row,column+1);
        point2.setZ(5);
        Computer.points.add(point2);
        Point point3=new Point(row,column+2);
        point3.setZ(5);
        Computer.points.add(point3);
        Point point4=new Point(row,column+3);
        point4.setZ(5);
        Computer.points.add(point4);
        Point point5=new Point(row,column+4
        );
        point5.setZ(5);
        Computer.points.add(point5);
    }
}
