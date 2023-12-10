package Main;

public class Point {
int x;
int y;
int z;
//1 submarine,2 destroyer,3 battleship,4 carrier,5 cruiser
    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }
    public void setZ(int z){
        this.z=z;
    }

    public int getZ() {
        return z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
