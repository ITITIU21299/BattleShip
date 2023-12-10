package Main;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class Computer {
    private static final int MAX_SHIPS = 5;
    private static final int  EMPTY_POSITION =  0 ;
    private static final int  OCCUPIED_POSITION =  1 ;

    public Set<String> Submarine = new HashSet<>();
    public  Set<String> BattleShip = new HashSet<>();
    public  Set<String> Cruiser = new HashSet<>();
    public  Set<String> carrier = new HashSet<>();
    public  Set<String> Destroyer = new HashSet<>();

    int sunk=2;

    public static int  [][] positions;
    public static int ships;

    public Computer() {
        ships = 5;
        positions = new int[BoardGame.NUMBER_HORIZONTAL][BoardGame.NUMBER_VERTICAL];
        for (int i = 0; i < BoardGame.NUMBER_HORIZONTAL; i++) {
            for (int j = 0; j < BoardGame.NUMBER_VERTICAL; j++) {
                positions[i][j] = EMPTY_POSITION;
            }
        }
        make_Destroyer();
        make_Carrier();
        make_BattleShip();
        make_Cruiser();
        makeSubmarine();

        System.out.println("Matrix  is ");
        for (int i = 0; i < BoardGame.NUMBER_HORIZONTAL; i++) {
            for (int j = 0; j < BoardGame.NUMBER_VERTICAL; j++) {
                System.out.printf(" %d ",positions[i][j]);
            }
            System.out.println();
        }
    }

    public void makeSubmarine(){
        boolean check1=false;
        boolean check2=false;
        int row,column;
        Random random=new Random();
        while(true) {
             row = random.nextInt(9);
             column = random.nextInt(9);
            if(row!=9&column!=0&&column!=9){
                check1=true;
            }else {
                continue;
            }

            if(positions[row][column] ==0 &&
                    positions[row + 1][column] ==0&&
                    positions[row + 1][column + 1] == 0&&
                    positions[row + 1][column - 1] ==0 ){
                check2=true;
            }
            if(check1==true&&check2==true){
                break;
            }
        }
        positions[row][column] = 1;
        positions[row + 1][column] = 1;
        positions[row + 1][column + 1] = 1;
        positions[row + 1][column - 1] = 1;
        System.out.println("Submarine is ");
        for (int i = 0; i < BoardGame.NUMBER_HORIZONTAL; i++) {
            for (int j = 0; j < BoardGame.NUMBER_VERTICAL; j++) {
                System.out.printf(" %d ",positions[i][j]);
            }
            System.out.println();
        }
    }
public void make_BattleShip(){
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
            if( positions[row][column] == 0&&
                    positions[row ][column+1] == 0&&
                    positions[row ][column + 2] == 0&&
                    positions[row + 1][column - 1] == 0&&
                    positions[row +1 ][column] == 0 &&
                    positions[row +1][column+1] == 0 ){
                check2=true;
            }

            if(check1==true&&check2==true){
                break;
            }

        }
    positions[row][column] = 1;
    positions[row ][column+1] = 1;
    positions[row ][column + 2] = 1;
    positions[row + 1][column - 1] = 1;
    positions[row +1 ][column] = 1;
    positions[row +1][column+1] = 1;
    System.out.println("BattleShip is ");
    for (int i = 0; i < BoardGame.NUMBER_HORIZONTAL; i++) {
        for (int j = 0; j < BoardGame.NUMBER_VERTICAL; j++) {
            System.out.printf(" %d ",positions[i][j]);
        }
        System.out.println();
    }
}
public void make_Destroyer(){
    int row ,column;
    boolean check1=false;
    boolean check2=false;
    Random random=new Random();
    while(true) {
        row = random.nextInt(9);
        column = random.nextInt(9);
        if(row<9){
            check1=true;
        }else {
            continue;
        }
        if(positions[row][column] ==0&&positions[row+1][column] == 0 ){
            check2=true;
        }

        if(check1==true&&check2==true){
            break;
        }

    }
   positions[row][column] = 1;
    positions[row+1][column] = 1;
    System.out.println("Destroyer is ");
    for (int i = 0; i < BoardGame.NUMBER_HORIZONTAL; i++) {
        for (int j = 0; j < BoardGame.NUMBER_VERTICAL; j++) {
            System.out.printf(" %d ",positions[i][j]);
        }
        System.out.println();
    }
}

    public void make_Carrier(){
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
            if(positions[row][column] == 0&& positions[row+1][column] == 0&& positions[row+2][column] ==0&& positions[row+3][column] == 0&& positions[row+4][column] ==0 ){
                check2=true;
            }

            if(check1==true&&check2==true){
                break;
            }

        }
        positions[row][column] = 1;
        positions[row+1][column] = 1;
        positions[row+2][column] = 1;
        positions[row+3][column] = 1;
        positions[row+4][column] = 1;

        System.out.println("Carrier is ");
        for (int i = 0; i < BoardGame.NUMBER_HORIZONTAL; i++) {
            for (int j = 0; j < BoardGame.NUMBER_VERTICAL; j++) {
                System.out.printf(" %d ",positions[i][j]);
            }
            System.out.println();
        }
    }

    public void make_Cruiser(){
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
            if( positions[row][column] == 0&&
                    positions[row][column+1] ==0&&
                    positions[row][column+2] ==0&&
                    positions[row][column+3] == 0&&
                    positions[row][column+4] == 0 ){
                check2=true;
            }

            if(check1==true&&check2==true){
                break;
            }
        }
        positions[row][column] = 1;
        positions[row][column+1] = 1;
        positions[row][column+2] = 1;
        positions[row][column+3] = 1;
        positions[row][column+4] = 1;
System.out.println("Cruiser is ");
        for (int i = 0; i < BoardGame.NUMBER_HORIZONTAL; i++) {
            for (int j = 0; j < BoardGame.NUMBER_VERTICAL; j++) {
                System.out.printf(" %d ",positions[i][j]);
            }
            System.out.println();
        }
    }



}