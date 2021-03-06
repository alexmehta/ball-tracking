package Utility;

import Filters.clustering.Kluster;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class Serializer {
    static PrintWriter fout;
    //prevent creation of Serializer class, as it is a static class.
    private Serializer(){

    }
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
        }
    }

    public static void print(Pair<Integer, Integer> pair) {
        fout.println(getTime() + ", " + pair.getFirst() + ", " + pair.getSecond());
        fout.flush();
    }
}
