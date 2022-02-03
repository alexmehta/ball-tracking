package Filters.convolution;


import core.DImage;

public class CannyOperator {

    public short[][] canny(short[][] canny) throws Exception {
        DImage d = new DImage(canny[0].length, canny.length);
        d.setPixels(canny);
        Sobel sobel = new Sobel();
        short[][] v = sobel.processImage(d).getBWPixelGrid();
        return v;
    }
}
