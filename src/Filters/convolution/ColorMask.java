package Filters.convolution;

import Utility.Utility;
import core.DImage;

public class ColorMask extends Convolution {
    private final int BLACK = 0;
    private final int WHITE = 255;
    private double THRESHOLD = 200;
    public ColorMask() {
   }

    public double getTHRESHOLD() {
        return THRESHOLD;
    }

    public void setTHRESHOLD(double THRESHOLD) {
        this.THRESHOLD = THRESHOLD;
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();
        for (int r = 0; r < red.length; r++) {
            for (int c = 0; c < red[r].length; c++) {
                boolean isRed = Utility.distanceTo(255, 0, 0, red[r][c], green[r][c], blue[r][c]) <= THRESHOLD;
                boolean isBlue = Utility.distanceTo(0, 0, 255, red[r][c], green[r][c], blue[r][c]) <= THRESHOLD;
                boolean isGreen = Utility.distanceTo(0, 255, 0, red[r][c], green[r][c], blue[r][c]) <= THRESHOLD;
                if (isRed || isGreen || isBlue) {
                    red[r][c] = WHITE;
                    green[r][c] = WHITE;
                    blue[r][c] = WHITE;
                } else {
                    red[r][c] = BLACK;
                    green[r][c] = BLACK;
                    blue[r][c] = BLACK;
                }
            }
        }
        img.setColorChannels(red, green, blue);
        return img;
    }
}



