package Filters.convolution;


import core.DImage;

public class BasicOutline extends Convolution {

    private final double[][] outlineKernel = {{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};


    @Override
    public DImage processImage(DImage img) throws Exception {
        short[][] pixels = img.getBWPixelGrid();
        short[][] outputPixels;
        outputPixels = applyKernel(pixels, outlineKernel);
        img.setPixels(outputPixels);
        return img;
    }
}
