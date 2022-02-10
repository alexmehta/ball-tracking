package Filters.convolution.clustering;

import Utility.Pair;

import java.util.ArrayList;

public class Kluster {
    Pair<Integer, Integer> center;
    ArrayList<Pair<Integer, Integer>> pairs;

    public Kluster(Pair<Integer, Integer> center) {
        this.center = center;
        this.pairs = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Kluster{" + "center=" + center + '}';
    }

    public Pair<Integer, Integer> getCenter() {
        return center;
    }

    public void setCenter(Pair<Integer, Integer> center) {
        this.center = center;
    }

    //points
    public ArrayList<Pair<Integer, Integer>> getPairs() {
        return pairs;
    }

    public void setPairs(ArrayList<Pair<Integer, Integer>> pairs) {
        this.pairs = pairs;
    }

    public void addPoint(Pair<Integer, Integer> loc) {
        pairs.add(loc);
    }

    public void clear() {
        pairs.clear();
    }
}
