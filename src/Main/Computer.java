package Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class Computer {
    private static final int MAX_SHIPS = 5;
    private static final int  EMPTY_POSITION =  0 ;
    private static final int  OCCUPIED_POSITION =  1 ;


    public static ArrayList<Point> points;


    int sunk=2;

    public static int  [][] positions;
    public static int ships;
    Ship Ships[];


    public Computer() {
        points=new ArrayList<>();
        ships = 5;
        positions = new int[BoardGame.NUMBER_HORIZONTAL][BoardGame.NUMBER_VERTICAL];
        for (int i = 0; i < BoardGame.NUMBER_HORIZONTAL; i++) {
            for (int j = 0; j < BoardGame.NUMBER_VERTICAL; j++) {
                positions[i][j] = EMPTY_POSITION;
            }
        }
        Ships= new Ship[]{new SubMarine(), new Destroyer(), new BattleShip(), new Carrier(), new Cruiser()};
        for(Ship ship:Ships){
            ship.make_ship_computer();
        }

        System.out.println("Matrix  is ");
        for (int i = 0; i < BoardGame.NUMBER_HORIZONTAL; i++) {
            for (int j = 0; j < BoardGame.NUMBER_VERTICAL; j++) {
                System.out.printf(" %d ",positions[i][j]);
            }
            System.out.println();
        }
    }

    public  int  check(int x,int y){

        int result=0;
        for(Point point:points){
            if(point.getX()==x&&point.getY()==y){
                result= point.getZ();
            }
        }
        System.out.println(result);
        switch (result){
            case 1:
                JOptionPane.showMessageDialog(null, "You attacked  Submarine's component .");
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "Your attacked Destroyer's component .");
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Your attacked battleship's component .");
                break;
            case 4:
                JOptionPane.showMessageDialog(null, "Your attacked  carrier's component .");
                break;
            case 5:
                JOptionPane.showMessageDialog(null, "Your attacked  Cruiser's component .");
                break;
        }
        return result;

    }





}