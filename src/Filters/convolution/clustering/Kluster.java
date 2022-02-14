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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kluster)) return false;
        Kluster kluster = (Kluster) o;
        return getCenter().equals(kluster.getCenter());
    }

    @Override
    public int hashCode() {
        return getCenter().hashCode();
    }

    @Override
    public String toString() {
        return "Kluster{" +
                "center=" + center +
                ", pairs=" + pairs +
                '}';
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

    public void empty() {
        pairs.clear();
    }
}
