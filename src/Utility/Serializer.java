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

    public Serializer() throws FileNotFoundException {
    }

    static long getTime() {
        return new Date().getTime();
    }

//    public static void printAll(ArrayList<Pair<Integer, Integer>> pairs) throws FileNotFoundException {
//        for (Pair<Integer, Integer> pair : pairs) {
//            print(pair);
//        }
//        fout.close();
//    }

    public static void printAll(ArrayList<Kluster> clusters) throws FileNotFoundException {
        for (Kluster cluster : clusters) {
            print(cluster.getCenter());
        }
        fout.close();
    }

    public static void print(Pair<Integer, Integer> pair) {
        fout.println(getTime() + pair.toString());
        fout.close();
    }
}
