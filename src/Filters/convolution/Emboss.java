package Filters.convolution;


import core.DImage;

public class Emboss extends Convolution {


    private final double[][] embossKernel = {{-2, -1, 0}, {-1, 1, 1}, {0, 1, 2}};
    @Override
    public DImage processImage(DImage img) throws Exception {
        short[][] pixels = img.getBWPixelGrid();
        short[][] outputPixels;
        outputPixels = applyKernel(pixels, embossKernel);
        img.setPixels(outputPixels);
        return img;
    }
}
