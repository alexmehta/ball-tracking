package Filters;


import Utility.Utility;
import core.DImage;
import Interfaces.PixelFilter;

public class DoNothingFilter implements PixelFilter {

    @Override
    public DImage processImage(DImage img) {
        // we don't change the input image at all!
        return img;
    }
}

