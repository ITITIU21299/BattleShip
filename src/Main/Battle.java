package Main;

import Inputs.MyKeyListener;

import javax.swing.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Battle {
    public BoardGame boardGame;
    public MyKeyListener myKeyListener;
    public  int[][] player_fire;

    public  Set<String> Oval_player = new HashSet<>();
    public  Set<String> Oval_Computer = new HashSet<>();
    Computer computer;
    Player player;
    int carrier,battleship,destroyer,cruiser,submarine;
    int C_carrier,C_battleship,C_destroyer,C_cruiser,C_submarine;


    public Battle(BoardGame boardGame){
        this.boardGame=boardGame;
        player_fire=new int[1000][1000];
        myKeyListener=new MyKeyListener(boardGame);
        player=new Player();
        computer=new Computer();
        cruiser=0;
        submarine=0;
        destroyer=0;
        battleship=0;
        carrier=0;

        C_submarine=0;
        C_carrier =0;
        C_battleship=0;
        C_destroyer=0;
        C_cruiser=0;
    }
    public void Player_turn() {
        int row;
        int column;

        do {
            String input_y = JOptionPane.showInputDialog("Enter row_axis:");

            // Check if the user canceled the input or closed the dialog
            if (input_y == null) {
                JOptionPane.showMessageDialog(null, "Game terminated. Bye!");
                System.exit(0);
            }

            try {
                row = Integer.parseInt(input_y);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
                row = -1; // Set x to an invalid value to trigger re-entry
            }
        } while (row < 0); // Keep prompting until a valid integer is entered

        do {
            String input_x = JOptionPane.showInputDialog("Enter column_axis:");

            // Check if the user canceled the input or closed the dialog
            if (input_x == null) {
                JOptionPane.showMessageDialog(null, "Game terminated. Bye!");
                System.exit(0);
            }

            try {
                column = Integer.parseInt(input_x);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
                column = -1; // Set y to an invalid value to trigger re-entry
            }
        } while (column < 0); // Keep prompting until a valid integer is entered

        // Use x and y to calculate row and column
        int x = column * 64 + 32 + 832;
        int y = 64 * row + 128;

        Oval_player.add(x + "," + y);
        boardGame.repaint();

        if (computer.positions[row][column] == 1) {
            int result= computer.check(row,column);
            switch (result){
                case 1:
                    C_submarine++;
                    break;
                case 2:
                    C_destroyer++;
                    break;
                case 3:
                    C_battleship++;
                    break;
                case 4:
                    C_carrier++;
                    break;
                case 5:
                    C_cruiser++;
                    break;
            }
            computer.positions[row][column] = 2;
            if(C_submarine==4){
                JOptionPane.showMessageDialog(null, "You sunk submarine  ");
                computer.ships--;
                boardGame.repaint();
                submarine=0;
            }
            if(C_battleship==6){
                JOptionPane.showMessageDialog(null, "You sunk battleship ");
                computer.ships--;
                boardGame.repaint();
                battleship=0;

            }
            if(C_destroyer==2){
                JOptionPane.showMessageDialog(null, "You sunk destroyer ");
                Player.ships--;
                boardGame.repaint();
                destroyer=0;
            }
            if(C_carrier==5){
                JOptionPane.showMessageDialog(null, "You sunk carrier ");
                computer.ships--;
                boardGame.repaint();
                carrier=0;
            }
            if(C_cruiser==5){
                JOptionPane.showMessageDialog(null, "You sunk cruiser ");
                computer.ships--;
                boardGame.repaint();
                cruiser=0;
            }

            boardGame.repaint();
        } else {
            JOptionPane.showMessageDialog(null, "Your fire is not good");
        }
    }

    public void Computer_turn(){
        Random random=new Random();
        int row = random.nextInt(9);
        int column = random.nextInt(9);
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(player.positions[i][j]==1){
                    row=i;
                    column=j;
                }
            }
            System.out.println();
        }
        int x =column*64+32;
        int y=64*row+128;
        Oval_Computer.add(x + "," + y);
        boardGame.repaint();
        if(player.positions[row][column]==  1 ){
           int result= player.check(row,column);
           switch (result){
               case 1:
                   submarine++;
                   break;
               case 2:
                   destroyer++;
                   break;
               case 3:
                   battleship++;
                   break;
               case 4:
                   carrier++;
                   break;
               case 5:
                   cruiser++;
                   break;
           }
            player.positions[row][column]=  2 ;
            boardGame.repaint();
            if(submarine==4){
                JOptionPane.showMessageDialog(null, "Your submarine is sunk");
                Player.ships--;
                boardGame.repaint();
                submarine=0;
            }
            if(battleship==6){
                JOptionPane.showMessageDialog(null, "Your battleship is sunk");
                Player.ships--;
                boardGame.repaint();
                battleship=0;

            }
            if(destroyer==2){
                JOptionPane.showMessageDialog(null, "Your destroyer is sunk");
                Player.ships--;
                boardGame.repaint();
                destroyer=0;
            }
            if(carrier==5){
                JOptionPane.showMessageDialog(null, "Your carrier is sunk");
                Player.ships--;
                boardGame.repaint();
                carrier=0;
            }
            if(cruiser==5){
                JOptionPane.showMessageDialog(null, "Your cruiser is sunk");
                Player.ships--;
                boardGame.repaint();
                cruiser=0;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Opponent's fire is not good");
        }
    }
    public void fight(){
        System.out.println("The player ship "+ player.ships);
        System.out.println("The computer ship "+ computer.ships);
        int n=0;
        while(Computer.ships>0&&Player.ships>0){
            n++;
            JOptionPane.showMessageDialog(null, "Enter the coordinate to fire for the " + n+ " time ");

            Player_turn();
            Computer_turn();
        }
        if(computer.ships>player.ships){
            JOptionPane.showMessageDialog(null, "You lose ");
            System.exit(0);
        }else{
            JOptionPane.showMessageDialog(null, "Your win");
            System.exit(0);
        }
    }
    



}

