package Filters.convolution;


import Interfaces.PixelFilter;
import core.DImage;

public class Convolution implements PixelFilter {

    private double kernelWeight(double[][] kernel) {
        double sum = 0;
        for (double[] a : kernel) {
            for (double v : a) {
                sum += v;
            }
        }
        return sum;
    }

    short[][] applyKernel(short[][] pixels, double[][] kernel) {
        double weight = kernelWeight(kernel);
        short[][] output = new short[pixels.length][pixels[0].length];
        for (int i = Math.floorDiv(kernel.length, 2); i < pixels.length - Math.floorDiv(kernel.length, 2); i++) {
            for (int j = Math.floorDiv(kernel.length, 2); j < pixels[i].length - Math.floorDiv(kernel.length, 2); j++) {
                int sum = getSum(pixels, output, i, j, kernel);
                if (weight == 0) weight = 1;
                if (weight != 1) sum /= weight;
                output[i][j] = (short) (sum);
                if (output[i][j] < 0) output[i][j] = 0;
                else if (output[i][j] > 255) output[i][j] = 255;
            }
        }
        return output;
    }

    private int getSum(short[][] pixels, short[][] output, int i, int j, double[][] Kernel) {
        int sum = 0;
        int dist = Math.floorDiv(Kernel.length, 2);
        for (int k = -dist; k <= dist; k++) {
            for (int l = -dist; l <= dist; l++) {
                if (at(output, i + k, j + l)) sum += ((pixels[i + k][j + l]) * Kernel[k + dist][l + dist]);
            }
        }
        return sum;
    }

    private boolean at(short[][] output, int i, int j) {
        return i >= 0 && j >= 0 && i < output.length && j < output[0].length;
    }


    @Override
    public DImage processImage(DImage img) throws Exception {
        return null;
    }
}
