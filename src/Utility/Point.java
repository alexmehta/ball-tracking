package Utility;

public class Point<K, V> {
    K yCord;
    V xCord;

    //swapped values first is y coord
    public Point(K first, V second) {
        this.yCord = first;
        this.xCord = second;
    }

    public static void swap(Point p) {
        Object temp = p.yCord;
        p.yCord = p.xCord;
        p.xCord = temp;
    }


    public K getyCord() {
        return yCord;
    }

    public void setyCord(K yCord) {
        this.yCord = yCord;
    }

    public V getxCord() {
        return xCord;
    }

    public void setxCord(V xCord) {
        this.xCord = xCord;
    }

    @Override
    public String toString() {
        return xCord + ", " + yCord;
    }
}