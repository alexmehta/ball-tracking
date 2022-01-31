package Filters;


import core.DImage;
import Interfaces.Drawable;
import Interfaces.PixelFilter;
import processing.core.PApplet;

public class DrawingFilter implements PixelFilter, Drawable {

    @Override
    public DImage processImage(DImage img) {
        // we don't change the input image at all!
        return img;
    }


    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }
}

