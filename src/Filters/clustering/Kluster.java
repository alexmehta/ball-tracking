package Filters.clustering;

import Utility.Pair;

import java.util.ArrayList;

/**
 * Represents a single ball object as a Cluster in a K-means clustering algorithm.
 *
 * @see <a href="https://docs.google.com/presentation/d/1Fc8nYHWzw4wxqpDboB07TfbmMfKZ4LtOHLi5LzBO4v4/edit#slide=id.g1126f15ff71_0_0">Slides</a>
 **/
public class Kluster {
    Pair<Integer, Integer> center;
    ArrayList<Pair<Integer, Integer>> pairs;

    /**
     * Takes in a center value that represents the center of the ball.
     * The constructor also initializes an empty list
     * @see  Kluster#pairs
     * @param center is the center of the ball
     */
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
        return "Kluster{" + "center=" + center + ", pairs=" + pairs + '}';
    }

    public Pair<Integer, Integer> getCenter() {
        return center;
    }



    //points
    public ArrayList<Pair<Integer, Integer>> getPairs() {
        return pairs;
    }


    public void addPoint(Pair<Integer, Integer> loc) {
        pairs.add(loc);
    }

    public void empty() {
        pairs.clear();
    }
}
