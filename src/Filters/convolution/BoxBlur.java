package Filters.convolution;


import core.DImage;

import javax.swing.*;
import java.util.Arrays;

public class BoxBlur extends Convolution {
    int N;

    public BoxBlur() {
        this.N = Integer.parseInt(JOptionPane.showInputDialog("Select an odd number (kernel size)"));
        //if it is even then we need to increment by 1
        // (better to have a larger number than an error because of invalid kernel size or even worse a -1 kernel size by rounding down)
        if (this.N % 2 == 0) this.N++;
    }

    private double[][] boxBlur(int n) throws Exception {
        if (n % 2 == 0) throw new Exception("Even number N");
        double[][] boxblur = new double[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(boxblur[i], 1);
        }
        return boxblur;

    }

    @Override
    public DImage processImage(DImage img) throws Exception {
        short[][] pixels = img.getBWPixelGrid();
        short[][] outputPixels;
        outputPixels = applyKernel(pixels, boxBlur(N));
        img.setPixels(outputPixels);
        return img;
    }
}
