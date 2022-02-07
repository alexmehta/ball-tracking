package Utility;

import Filters.convolution.clustering.Kluster;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class Serializer {
    static PrintWriter fout;

    static {
        try {
            fout = new PrintWriter(new OutputStreamWriter(new FileOutputStream("locations.csv")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    static long getTime() {
        return new Date().getTime();
    }
    public static void printAll(ArrayList<Kluster> clusters) {
        for (Kluster cluster : clusters) {
            print(cluster.getCenter());
            System.out.println(cluster.getCenter());
        }
    }

    public static void print(Pair<Integer, Integer> pair) {
        fout.println(getTime() + ", " + pair.getY() + ", " + pair.getX());
        fout.flush();
    }
}
