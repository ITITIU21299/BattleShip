package Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


class Player {
    private static final int MAX_SHIPS = 5;
    private static final int EMPTY_POSITION =  0 ;
    private static final int OCCUPIED_POSITION =  1 ;
    public static Set<String> Submarine = new HashSet<>();
    public static Set<String> BattleShip = new HashSet<>();
    public static Set<String> Cruiser = new HashSet<>();
    public static Set<String> carrier = new HashSet<>();
    public static Set<String> Destroyer = new HashSet<>();
    int sunk=2;
     public static ArrayList<Point>points;
    public static int[][] positions;
    public  static int ships;

    public Player() {
        points=new ArrayList<>();
        ships = 0;
        positions = new int[BoardGame.NUMBER_HORIZONTAL][BoardGame.NUMBER_VERTICAL];
        for (int i = 0; i < BoardGame.NUMBER_HORIZONTAL; i++) {
            for (int j = 0; j < BoardGame.NUMBER_VERTICAL; j++) {
                positions[i][j] = EMPTY_POSITION;
            }
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
               JOptionPane.showMessageDialog(null, "Your Submarine was attacked.");
               break;
           case 2:
               JOptionPane.showMessageDialog(null, "Your Destroyer was attacked.");
               break;
           case 3:
               JOptionPane.showMessageDialog(null, "Your battleship was attacked.");
               break;
           case 4:
               JOptionPane.showMessageDialog(null, "Your carrier was attacked.");
               break;
           case 5:
               JOptionPane.showMessageDialog(null, "Your Cruiser was attacked.");
               break;
       }
       return result;

    }





}


