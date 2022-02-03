package Filters.convolution;

import Utility.Pair;
import core.DImage;

import java.util.ArrayList;
import java.util.Objects;

public class FindCenter extends Convolution {
    private static int height;
    private static int width;
    private static int size;

    public FindCenter() {
    }

    public static Pair<Integer, Integer> count(DImage img) {
        height = img.getHeight();
        width = img.getWidth();
        short[][] grid = img.getBWPixelGrid();
        int count = 0;
        img.getBWPixelGrid();
        Pair<Integer, Integer> pair = new Pair<>(0, 0);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == 0) {
                    pair.setSecond(pair.getSecond() + i);
                    pair.setFirst(pair.getFirst() + j);
                    count++;
                }

            }
        }
        pair.setFirst(pair.getFirst() / count);
        pair.setSecond(pair.getSecond() / count);

        return pair;
    }

    public static Pair<Integer, Integer> count(ArrayList<Pair<Integer, Integer>> points) {
        size = points.size();
        int sumX = 0;
        int sumY = 0;

        Pair<Integer, Integer> avg = new Pair<>(0, 0);
        for (int i = 0; i < size; i++) {
            sumX +=points.get(i).getFirst();
            sumY +=points.get(i).getSecond();
        }

        int xCor = sumX/size;
        int yCor = sumY/size;

        avg.setFirst(xCor);
        avg.setSecond(yCor);

        return avg;

    }
}