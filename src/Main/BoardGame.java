package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.Scanner;
//1 Ships of player
//2 Ships of computer

public class BoardGame extends JPanel {
    public static int playerShips;
    public static int computerShips;
    public static String[][] Player_position = new String[10][10];
    public static String[][] Computer_position = new String[10][10];
    public BoardGame(){


    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        int number_horizontal = 10;
        int number_vertical = 10;
        int gridsize = 64;
        int startX = 32;
        g.setColor(Color.blue);
        g.fillRect(32,64*2,640,getHeight());
        g.fillRect(864,64*2,640,getHeight());

        g.setColor(Color.cyan);

        for (int i = 0; i <= number_horizontal; i++) {
            int x = startX + i * gridsize;
            g.drawLine(x, 64*2, x, getHeight());
        }
        for (int i = 0; i <= number_horizontal; i++) {
            int x = 864 + i * gridsize;
            g.drawLine(x, 64*2, x, getHeight());
        }
        for (int i = 0; i <= number_vertical; i++) {
            g.drawLine(32,64*2+64*i,672,64*2+64*i);
        }
        for (int i = 0; i <= number_vertical; i++) {
            g.drawLine(864,64*2+64*i,1504,64*2+64*i);
        }
        drawTableTitle(g, "Your  Table", 100, 64 * 2);
        drawTableTitle(g, "Opponent Table", 1100, 64 * 2);
drawGraphics(g,"Match",672,100);



    }
     void drawTableTitle(Graphics g, String title, int x, int y) {
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(title, x, y - 10);
    }
    void drawGraphics (Graphics g, String title, int x, int y) {
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(title, x, y - 10);
    }

    public static void deployPlayerShips(){
        Scanner input = new Scanner(System.in);
        for (int i = 1; i <= 5; ) {
            System.out.print("Enter X coordinate for your " + i + " ship: ");
            int x = input.nextInt();
            System.out.print("Enter Y coordinate for your " + i + " ship: ");
            int y = input.nextInt();

            if((x >= 0 && x < 10) && (y >= 0 && y < 10) && (Player_position[x][y] == " "))
            {
                Player_position[x][y] =   "1";
                i++;
            }
            else if((x >= 0 && x < 10) && (y >= 0 && y < 10) && Player_position[x][y] == "1")
                System.out.println("You can't place two or more ships on the same location");
            else if((x < 0 || x >= 10) || (y < 0 || y >= 10))
                System.out.println("You can't place ships outside the " + 10 + " by " + 10 + " grid");
        }

    }
    public static void deployComputerShips(){
        System.out.println("\nComputer is deploying ships");
        //Deploying five ships for computer

        for (int i = 1; i <= 5; ) {
            int x = (int)(Math.random() * 10);
            int y = (int)(Math.random() * 10);

            if((x >= 0 && x < 10) && (y >= 0 && y < 10) && (Computer_position[x][y] == " "))
            {
                Computer_position[x][y] =   "2";
                System.out.println(i + ". ship DEPLOYED");
                i++;
            }
        }
    }


}
