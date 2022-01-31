package Filters.convolution;


import core.DImage;

public class Sobel extends Convolution {

    private final double[][] Gx = {{-1, 0, +1}, {-2, 0, +2}, {-1, 0, +1}};
    private final double[][] Gy = {{1, 2, 1}, {0, 0, 0}, {-1, -2, -1}};

    @Override
    public DImage processImage(DImage img) throws Exception {
        short[][] pixels = img.getBWPixelGrid();
        short[][] outputPixels;
        outputPixels = sobel(applyKernel(pixels, Gx), applyKernel(pixels, Gy));
        img.setPixels(outputPixels);
        return img;
    }

    public short[][] sobel(short[][] Gx, short[][] Gy) {
        short[][] out = new short[Gx.length][Gx[0].length];
        for (int i = 0; i < Gx.length; i++) {
            for (int j = 0; j < Gx[0].length; j++) {
                out[i][j] = (short) Math.sqrt((Gx[i][j] * Gx[i][j]) + (Gy[i][j] * Gy[i][j]));
            }
        }
        return out;
    }
}
