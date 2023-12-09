package Main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;
import Inputs.MyKeyListener;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private BufferedImage mainMenuImage;
    private BufferedImage specialAreaImage,ExitImage;

    // Add variables to store the coordinates of the special area
    private int specialAreaX = 480;
    private int specialAreaY = 640;
    private int specialAreaWidth = 100;
    private int specialAreaHeight = 50;

    int Exit_x=480;
    int Exit_y=700;
    int ExitWidth=150;
    int Exit_Height=150;

    private boolean drawSpecialArea,Exit_Area = false;

    public GamePanel() {
        initializeImages();
        mouseInputs = new MouseInputs();
        addKeyListener(new KeyboardInputs());

        // Add both MouseListener and MouseMotionListener to handle click and move
        addMouseListener(new CustomMouseListener());
        addMouseMotionListener(new CustomMouseMotionListener());
    }

    private void initializeImages() {
        try {
            mainMenuImage = ImageIO.read(new File("src/Resources/MainMenu.jpg"));
            specialAreaImage = ImageIO.read(new File("src/Resources/Play.jpg"));
            ExitImage=ImageIO.read(new File("src/Resources/Exit.jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Custom MouseMotionListener to handle mouse movement
    private class CustomMouseMotionListener implements MouseMotionListener {
        @Override
        public void mouseMoved(MouseEvent e) {
            // Check if the mouse is within the special area
            drawSpecialArea = (e.getX() >= specialAreaX && e.getX() <= specialAreaX + specialAreaWidth &&
                    e.getY() >= specialAreaY && e.getY() <= specialAreaY + specialAreaHeight);

            Exit_Area=(e.getX() >= Exit_x && e.getX() <= ExitWidth + Exit_x &&
                    e.getY() >= Exit_y && e.getY() <= Exit_y + Exit_Height);
            repaint();  // Trigger a repaint when the mouse moves
        }


        @Override
        public void mouseDragged(MouseEvent e) {
            // Handle mouse drag if needed
        }
    }

    // Custom MouseListener to handle mouse clicks
    private class CustomMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            // Handle mouse click actions here
            if (drawSpecialArea) {
                // Retrieve the parent JFrame
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(GamePanel.this);

                // Close the parent JFrame
                parentFrame.dispose();

                // Create a new JFrame with the BoardGame
                JFrame newFrame = new JFrame("Match");
                BoardGame boardGame = new BoardGame();
                MyKeyListener myKeyListener = new MyKeyListener(boardGame);

                newFrame.add(boardGame);
                newFrame.setSize(1600, 900);
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the entire application on new frame close
                newFrame.setVisible(true);

                boardGame.requestFocus();
            }
            if(Exit_Area){
                System.exit(1);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // Handle mouse press actions if needed
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // Handle mouse release actions if needed
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // Handle mouse entering the panel if needed
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // Handle mouse exiting the panel if needed
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (drawSpecialArea) {
            drawImage(g, specialAreaImage);
        } else if(Exit_Area){
            drawImage(g,ExitImage);
        }else{
            drawImage(g,mainMenuImage);
        }
    }

    private void drawImage(Graphics g, Image image) {
        Image scaledImage = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        g.drawImage(scaledImage, 0, 0, null);
    }
}
