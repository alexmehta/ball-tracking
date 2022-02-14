package Filters.clustering;

import Utility.Pair;
import Utility.Utility;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class Klustering {
    //num klusters
    int kP;
    ArrayList<Kluster> klusters;
    ArrayList<Pair<Integer, Integer>> targets;
    boolean notFinished = true;
    ArrayList<Kluster> oldKlusters = new ArrayList<>();
    short[][] img;

    public Klustering(int kP, short[][] image) {
        this.kP = kP;
        this.img = image;

    }

    public static Pair<Integer, Integer> count(ArrayList<Pair<Integer, Integer>> i) {
        Pair<Integer, Integer> pair = new Pair<>(0, 0);
        int count = i.size();
        for (Pair<Integer, Integer> c : i) {
            pair = Pair.add(c, pair);
        }
        pair.setFirst(pair.getFirst() / count);
        pair.setSecond(pair.getSecond() / count);
        return pair;
    }

    public ArrayList<Kluster> getKlusters() {
        return klusters;
    }

    public void kluster() {
        initClusters();//create empty clusters wtih random centers
        targets = getWhites(img);
        do {
            clearClusterPoints();
            System.out.println(klusters);
            assignPoints(targets);
            centerKlusters();
        } while (notFinished);
    }

    private void initClusters() {
        klusters = new ArrayList<>();
        targets = getWhites(img);
        Collections.shuffle(targets);
        Queue<Pair<Integer, Integer>> locations = new ArrayDeque<Pair<Integer, Integer>>(targets);
        if (locations.size() == 0) return;
        for (int i = 0; i < kP; i++) {
            klusters.add(new Kluster(locations.poll()));
        }
    }

    private void centerKlusters() {
        for (Kluster cluster : klusters) {
            Pair<Integer, Integer> newcenter = getAverage(cluster.pairs);
            notFinished = newcenter.getFirst().intValue() != cluster.getCenter().getFirst().intValue() || newcenter.getSecond().intValue() != cluster.getCenter().getSecond().intValue();
            cluster.center = count(cluster.pairs);
        }
    }

    private Pair<Integer, Integer> getAverage(ArrayList<Pair<Integer, Integer>> pairs) {
        Pair<Integer, Integer> avg = new Pair<Integer, Integer>(0, 0);

        for (Pair<Integer, Integer> pair : pairs) {
            avg = Pair.add(pair, avg);
        }

        avg.setFirst(avg.getFirst() / pairs.size());
        avg.setSecond(avg.getSecond() / pairs.size());
        return avg;
    }

    private void assignPoints(ArrayList<Pair<Integer, Integer>> targets) {
        for (Pair<Integer, Integer> target : targets) {
            double smallestDist = Double.MAX_VALUE;
            int smallestIdx = -1;
            for (int i = 0; i < klusters.size(); i++) {
                double dist = Utility.distanceTo(target.getFirst(), target.getSecond(), 0, klusters.get(i).center.getFirst(), klusters.get(i).center.getSecond(), 0);
                if (dist < smallestDist) {
                    smallestIdx = i;
                    smallestDist = dist;
                }
            }
            klusters.get(smallestIdx).addPoint(target);
        }
    }

    private void clearClusterPoints() {
        oldKlusters = new ArrayList<>(klusters);
        for (Kluster cluster : klusters) {
            cluster.empty();
        }
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