package Filters.convolution.clustering;

import Utility.Point;

import java.util.ArrayList;

public class Kluster {
    Point<Integer, Integer> center;
    ArrayList<Point<Integer, Integer>> pairs;

    public Kluster(Point<Integer, Integer> center) {
        this.center = center;
        this.pairs = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Kluster{" +
                "center=" + center +
                '}';
    }

    public Point<Integer, Integer> getCenter() {
        return center;
    }

    public void setCenter(Point<Integer, Integer> center) {
        this.center = center;
    }
    //points
    public ArrayList<Point<Integer, Integer>> getPairs() {
        return pairs;
    }

    public void setPairs(ArrayList<Point<Integer, Integer>> pairs) {
        this.pairs = pairs;
    }

    public void addPoint(Point<Integer, Integer> loc) {
        pairs.add(loc);
    }

    public void clear() {
        pairs.clear();
    }
}
