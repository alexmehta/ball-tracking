package Filters.convolution.clustering;

import Filters.convolution.FindCenter;
import Utility.Pair;
import Utility.Utility;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class Klustering {
    int kP;
    ArrayList<Kluster> clusters;
    ArrayList<Pair<Integer, Integer>> targets;
    ArrayList<Pair<Integer, Integer>> prev = new ArrayList<Pair<Integer, Integer>>();
    boolean done = true;

    public Klustering(int kP, short[][] image) {
        this.kP = kP;
        clusters = new ArrayList<>();
        targets = getWhites(image);
        Collections.shuffle(targets);
        Queue<Pair<Integer, Integer>> locations = new ArrayDeque<Pair<Integer, Integer>>(targets);
        if (locations.size() == 0) return;
        for (int i = 0; i < kP; i++) {
            clusters.add(new Kluster(locations.poll()));
        }
        targets = getWhites(image);
    }

    public ArrayList<Kluster> getClusters() {
        return clusters;
    }


    public void kluster() {
        int i = 0;
        do {
            clearClusters();
            assignPoints(targets);
            centerKlusters();
            i++;
        } while (i != 20);
    }


    private void centerKlusters() {
        for (Kluster cluster : clusters) {
            prev.add(cluster.center);
            Pair<Integer, Integer> newcenter = getAverage(cluster.pairs);
            if (newcenter.equals(cluster.center)) done = false;
            cluster.center = FindCenter.count(cluster.pairs);
        }
    }

    private Pair<Integer, Integer> getAverage(ArrayList<Pair<Integer, Integer>> pairs) {
        Pair<Integer, Integer> avg = new Pair<Integer, Integer>(0, 0);
        for (Pair<Integer, Integer> pair : pairs) {
            avg.add(pair);
        }
        avg.setFirst(avg.getFirst() / pairs.size());
        avg.setSecond(avg.getSecond() / pairs.size());
        return avg;
    }

    private void assignPoints(ArrayList<Pair<Integer, Integer>> targets) {
        for (Pair<Integer, Integer> target : targets) {
            double smallest = Double.MAX_VALUE;
            int smallestidx = -1;
            for (int i = 0; i < clusters.size(); i++) {
                double dist = Utility.distanceTo(target.getFirst(), target.getSecond(), 0, clusters.get(i).center.getFirst(), clusters.get(i).center.getSecond(), 0);
                if (dist < smallest) {
                    smallestidx = i;
                    smallest = dist;
                }
            }
            clusters.get(smallestidx).addPoint(target);
        }
    }

    private void clearClusters() {
        for (Kluster cluster : clusters) {
            cluster.clear();
        }
        prev.clear();
    }

    private ArrayList<Pair<Integer, Integer>> getWhites(short[][] image) {
        ArrayList<Pair<Integer, Integer>> c = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                short v = image[i][j];
                if (v != 255) continue;
                c.add(new Pair<>(i, j));
            }
        }
        return c;
    }
}
