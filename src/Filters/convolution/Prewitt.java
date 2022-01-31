package Filters.convolution;


import core.DImage;

import java.util.Arrays;

public class Prewitt extends Convolution {
    private double[][] prewitt(int n) throws Exception {
        if (n % 2==0) throw new Exception("Even number N");
        double[][] prewitt = new double[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(prewitt[i], -1);
        }
        prewitt[n / 2][n / 2] = n * n - 1;
        return prewitt;
    }
    @Override
    public DImage processImage(DImage img) throws Exception {
        short[][] pixels = img.getBWPixelGrid();
        short[][] outputPixels;

        outputPixels = applyKernel(pixels,prewitt(7));
        img.setPixels(outputPixels);
        return img;
    }

}
