package Filters.convolution;


import core.DImage;

public class BasicBlur extends Convolution {

    private final double[][] blurKernel = {{1.0 / 9, 1.0 / 9, 1.0 / 9}, {1.0 / 9, 1.0 / 9, 1.0 / 9}, {1.0 / 9, 1.0 / 9, 1.0 / 9}};

    @Override
    public DImage processImage(DImage img) throws Exception {
        short[][] pixels = img.getBWPixelGrid();
        short[][] outputPixels;
        outputPixels = applyKernel(pixels, blurKernel);
        img.setPixels(outputPixels);
        return img;
    }
}
