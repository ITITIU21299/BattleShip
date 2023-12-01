package Main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JPanel;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private BufferedImage img;

    public GamePanel() {

        importImg();

        mouseInputs = new MouseInputs();

        addKeyListener(new KeyboardInputs());
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void importImg() {
        //InputStream is = getClass().getResourceAsStream("Resources/MainMenu.jpg");
        try {
            img = ImageIO.read(new FileInputStream("src/Resources/MainMenu.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image scaledImage = img.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH);
        g.drawImage(scaledImage, 0, 0, null);
        // g.drawRect(100, 100, 50, 200);
    }
}