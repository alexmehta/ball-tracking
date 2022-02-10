package Interfaces;

import core.DImage;

public interface PixelFilter {
    /***
     * apply a filter to an input image and return the filtered image as output
     * @param img the image to filter
     * @return the filtered image
     */
    DImage processImage(DImage img) throws Exception;
}