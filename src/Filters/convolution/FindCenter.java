package Filters.convolution;

import Utility.Pair;
import core.DImage;

import java.util.ArrayList;

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
                    pair.setX(pair.getX() + i);
                    pair.setY(pair.getY() + j);
                    count++;
                }

            }
        }
        pair.setY(pair.getY() / count);
        pair.setX(pair.getX() / count);

        return pair;
    }

    public static Pair<Integer, Integer> count(ArrayList<Pair<Integer, Integer>> points) {
        size = points.size();
        int sumX = 0;
        int sumY = 0;
        //return center use k means cluster
        Pair<Integer, Integer> avg = new Pair<>(0, 0);
        for (int i = 0; i < size; i++) {
            sumX +=points.get(i).getY();
            sumY +=points.get(i).getX();
        }

        int xCor = sumX/size;
        int yCor = sumY/size;

        avg.setY(xCor);
        avg.setX(yCor);

        return avg;

    }
}