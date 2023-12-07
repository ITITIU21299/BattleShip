package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

public class BoardGame extends JPanel {

    public BoardGame(){

    }




    public void paintComponent(Graphics g){

        super.paintComponent(g);
        int number_horizontal = 10;
        int number_vertical = 10;
        int gridsize = 64;
        int startX = 32;

        // Draw vertical lines
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


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Battle");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1600, 900);
            frame.getContentPane().add(new BoardGame());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }


}
