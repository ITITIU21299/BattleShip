package Main;

import javax.swing.*;

import java.util.Random;

import static Main.Player.*;

public class Carrier extends Ship{
    @Override
    public  void make_ship_player(int row, int column) {
        super.make_ship_player( row, column);
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

        Point point1=new Point(row,column);
        point1.setZ(4);
        points.add(point1);
        Point point2=new Point(row+1,column);
        point2.setZ(4);
        points.add(point2);
        Point point3=new Point(row+2,column);
        point3.setZ(4);
        points.add(point3);
        Point point4=new Point(row+3,column);
        point4.setZ(4);
        points.add(point4);
        Point point5=new Point(row+4,column);
        point5.setZ(4);
        points.add(point5);
    }

    @Override
    public  void make_ship_computer() {
        super.make_ship_computer();
        int row ,column;
        boolean check1=false;
        boolean check2=false;
        Random random=new Random();
        while(true) {
            row = random.nextInt(9);
            column = random.nextInt(9);
            if(row<6){
                check1=true;
            }else {
                continue;
            }
            if(Computer.positions[row][column] == 0&& Computer.positions[row+1][column] == 0&& Computer.positions[row+2][column] ==0&& Computer.positions[row+3][column] == 0&& Computer.positions[row+4][column] ==0 ){
                check2=true;
            }

            if(check1==true&&check2==true){
                break;
            }

        }
        Computer.positions[row][column] = 1;
        Computer.positions[row+1][column] = 1;
        Computer.positions[row+2][column] = 1;
        Computer.positions[row+3][column] = 1;
        Computer.positions[row+4][column] = 1;

        System.out.println("Carrier is ");
        for (int i = 0; i < BoardGame.NUMBER_HORIZONTAL; i++) {
            for (int j = 0; j < BoardGame.NUMBER_VERTICAL; j++) {
                System.out.printf(" %d ",Computer.positions[i][j]);
            }
            System.out.println();
        }
        Point point1=new Point(row,column);
        point1.setZ(4);
        Computer.points.add(point1);
        Point point2=new Point(row+1,column);
        point2.setZ(4);
        Computer.points.add(point2);
        Point point3=new Point(row+2,column);
        point3.setZ(4);
        Computer.points.add(point3);
        Point point4=new Point(row+3,column);
        point4.setZ(4);
        Computer.points.add(point4);
        Point point5=new Point(row+4,column);
        point5.setZ(4);
        Computer.points.add(point5);
    }
}
