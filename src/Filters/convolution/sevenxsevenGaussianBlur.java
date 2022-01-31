
package Filters.convolution;


import core.DImage;

public class sevenxsevenGaussianBlur extends Convolution {

    private final double[][] gaussianBlur = {{0, 0, 1, 2, 1, 0, 0}, {0, 3, 13, 22, 13, 3, 0}, {1, 13, 59, 97, 59, 13, 1}, {2, 22, 97, 159, 97, 22, 2}, {1, 13, 5, 9, 97, 59, 13, 1}, {0, 3, 13, 22, 13, 3, 0}, {0, 0, 1, 2, 1, 0, 0}};

    @Override
    public DImage processImage(DImage img) throws Exception {
        short[][] pixels = img.getBWPixelGrid();
        short[][] outputPixels;
        outputPixels = applyKernel(pixels,gaussianBlur );
        img.setPixels(outputPixels);
        return img;
    }
}

