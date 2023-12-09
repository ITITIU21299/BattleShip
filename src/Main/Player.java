package Main;

import java.awt.*;

class Player {
    private static final int MAX_SHIPS = 5;
    private static final int EMPTY_POSITION =  0 ;
    private static final int OCCUPIED_POSITION =  1 ;

    public static int[][] positions;
    public static  int ships;

    public Player() {
        ships = 0;
        positions = new int[BoardGame.NUMBER_HORIZONTAL][BoardGame.NUMBER_VERTICAL];
        for (int i = 0; i < BoardGame.NUMBER_HORIZONTAL; i++) {
            for (int j = 0; j < BoardGame.NUMBER_VERTICAL; j++) {
                positions[i][j] = EMPTY_POSITION;
            }
        }
    }

    public int getShips() {
        return ships;
    }



    public void incrementShips() {
        ships++;
    }


    public void occupyPosition(int row, int column) {
        positions[row][column] = OCCUPIED_POSITION;
    }

    public void drawShips(Graphics g) {
        // Drawing player ships logic
    }
}


