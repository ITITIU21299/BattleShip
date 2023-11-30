package Main;

import javax.swing.JPanel;

import Inputs.KeyboardInputs;

public class GamePanel extends JPanel {
    public GamePanel(){
        addKeyListener(new KeyboardInputs());
    }
}
