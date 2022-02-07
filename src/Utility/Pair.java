package Utility;

public class Pair<K, V> {
    K y;
    V x;

    public Pair(K first, V second) {
        this.y = first;
        this.x = second;
    }

    public static void swap(Pair p) {
        Object temp = p.y;
        p.y = p.x;
        p.x = temp;
    }


    public K getY() {
        return y;
    }

    public void setY(K y) {
        this.y = y;
    }

    public V getX() {
        return x;
    }

    public void setX(V x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}