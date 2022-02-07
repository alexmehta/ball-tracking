package Filters.convolution.clustering;

import Filters.convolution.FindCenter;
import Utility.Pair;
import Utility.Utility;

import java.util.ArrayList;
import java.util.Collections;

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
        for (int i = 0; i < kP; i++) {
            if (targets.size()==0) break;
            Collections.shuffle(targets);
            clusters.add(new Kluster(targets.get(0)));
            targets.remove(0);
        }
        targets = getWhites(image);
    }

    public int getkP() {return kP;
    }

    public void setkP(int kP) {
        this.kP = kP;
    }

    public ArrayList<Kluster> getClusters() {
        return clusters;
    }

    public void setClusters(ArrayList<Kluster> clusters) {
        this.clusters = clusters;
    }

    public ArrayList<Pair<Integer, Integer>> getTargets() {
        return targets;
    }

    public void setTargets(ArrayList<Pair<Integer, Integer>> targets) {
        this.targets = targets;
    }

    public ArrayList<Pair<Integer, Integer>> getPrev() {
        return prev;
    }

    public void setPrev(ArrayList<Pair<Integer, Integer>> prev) {
        this.prev = prev;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void kluster() {
        do {
            clearClusters();
            assignPoints(targets);
            centerKlusters();
        } while (!done);
    }


    private void centerKlusters() {
        for (Kluster cluster : clusters) {
            prev.add(cluster.center);
            Pair<Integer, Integer> newcenter = FindCenter.count(cluster.pairs);
            if (newcenter.equals(cluster.center)) done = false;
            cluster.center = FindCenter.count(cluster.pairs);
        }
    }

    private void assignPoints(ArrayList<Pair<Integer, Integer>> targets) {
        for (Pair<Integer, Integer> target : targets) {
            double smallest = Double.MAX_VALUE;
            int smallestidx = -1;
            for (int i = 0; i < clusters.size(); i++) {
                double dist = Utility.distanceTo(target.getY(), target.getX(), 0, clusters.get(i).center.getY(), clusters.get(i).center.getX(), 0);
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
