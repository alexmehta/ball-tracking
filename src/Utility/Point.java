package Utility;

public class Point<K, V> {
    K yCord;
    V xCord;

    //swapped values {{|}} first is y coordinate
    public Point(K first, V second) {
        this.yCord = first;
        this.xCord = second;
    }

    public static void swap(Point p) {
        Object temp = p.yCord;
        p.yCord = p.xCord;
        p.xCord = temp;
    }


    public K getY() {
        return yCord;
    }

    public void setY(K yCord) {
        this.yCord = yCord;
    }

    public V getX() {
        return xCord;
    }

    public void setX(V xCord) {
        this.xCord = xCord;
    }

    @Override
    public String toString() {
        //print point
        return xCord + ", " + yCord;
    }
}