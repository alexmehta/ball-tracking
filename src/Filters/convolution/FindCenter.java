package Filters.convolution;

import Utility.Point;
import core.DImage;

import java.util.ArrayList;

public class FindCenter extends Convolution {
    private static int height;
    private static int width;
    private static int size;

    public FindCenter() {
    }

    public static Point<Integer, Integer> count(DImage img) {
        height = img.getHeight();
        width = img.getWidth();
        short[][] grid = img.getBWPixelGrid();
        int count = 0;
        img.getBWPixelGrid();
        Point<Integer, Integer> pair = new Point<>(0, 0);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == 0) {
                    pair.setxCord(pair.getxCord() + i);
                    pair.setyCord(pair.getyCord() + j);
                    count++;
                }

            }
        }
        pair.setyCord(pair.getyCord() / count);
        pair.setxCord(pair.getxCord() / count);

        return pair;
    }

    public static Point<Integer, Integer> count(ArrayList<Point<Integer, Integer>> points) {
        size = points.size();
        int sumX = 0;
        int sumY = 0;
        //return center use k means cluster
        Point<Integer, Integer> avg = new Point<>(0, 0);
        for (int i = 0; i < size; i++) {
            sumX +=points.get(i).getyCord();
            sumY +=points.get(i).getxCord();
        }

        int xCor = sumX/size;
        int yCor = sumY/size;

        avg.setyCord(xCor);
        avg.setxCord(yCor);

        return avg;

    }
}