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
    public Battle(BoardGame boardGame){
        this.boardGame=boardGame;
        player_fire=new int[1000][1000];
        myKeyListener=new MyKeyListener(boardGame);
        player=new Player();
        computer=new Computer();
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
            JOptionPane.showMessageDialog(null, "Your fire is good");

            computer.positions[row][column] = 2;
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
            JOptionPane.showMessageDialog(null, "Your ship is attacked");
            player.positions[row][column]=  2 ;
            boardGame.repaint();
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

