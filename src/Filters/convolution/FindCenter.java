package Filters.convolution;

import Utility.Pair;
import core.DImage;

public class FindCenter extends Convolution {
    private static int height;
    private static int width;

    public FindCenter() {
    }

    public static Pair<Integer, Integer> count(DImage img) {
        height = img.getHeight();
        width = img.getWidth();
        short[][] grid = img.getBWPixelGrid();
        int count = 0;
        img.getBWPixelGrid();
        Pair<Integer, Integer> pair = new Pair<>(0,0);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == 0){
                    pair.setSecond(pair.getSecond()+j);
                    pair.setFirst(pair.getFirst()+i);
                    count++;
                }

            }
        }
        pair.setFirst(pair.getFirst()/count);
        pair.setSecond(pair.getSecond()/count);

        return pair;
    }
}