package Utility;

public class Pair<K, V> {
    K first;
    V second;

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public static void swap(Pair p) {
        Object temp = p.first;
        p.first = p.second;
        p.second = temp;
    }


    public K getFirst() {
        return first;
    }

    public void setFirst(K first) {
        this.first = first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return second + ", " + first;
    }

    public void add(Pair<K, V> pair) {
        this.setFirst(pair.getFirst());
        this.setSecond(pair.getSecond());
    }
}