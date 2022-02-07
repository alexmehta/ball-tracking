package Filters.convolution;

import core.DImage;

public class ColorMask extends Convolution {
    private final int BLACK = 0;
    private final int WHITE = 255;
    private final int red = 255;
    private final int green = 0;
    private final int blue = 0;
    private double THRESHOLD = 150;

    public ColorMask() {
//        String r = JOptionPane.showInputDialog("Enter red(0-255): ");
//        String g = JOptionPane.showInputDialog("Enter Green(0-255): ");
//        String b = JOptionPane.showInputDialog("Enter Blue(0-255): ");
//        this.red = Integer.parseInt(r);
//        this.green = Integer.parseInt(g);
//        this.blue = Integer.parseInt(b);
    }

    public double getTHRESHOLD() {
        return THRESHOLD;
    }

    public void setTHRESHOLD(double THRESHOLD) {
        this.THRESHOLD = THRESHOLD;
    }

    public double distanceTo(int r, int g, int b, int otherR, int otherG, int otherB) {
        double dR = Math.abs(r - otherR) * Math.abs(r - otherR);
        double dG = Math.abs(g - otherG) * Math.abs(g - otherG);
        double dB = Math.abs(b - otherB) * Math.abs(b - otherB);
        return Math.sqrt(dR + dG + dB);

    }

    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();
        for (int r = 0; r < red.length; r++) {
            for (int c = 0; c < red[r].length; c++) {
                boolean isRed = distanceTo(255, 0, 0, red[r][c], green[r][c], blue[r][c]) <= THRESHOLD;
                boolean isBlue = distanceTo(0, 0, 255, red[r][c], green[r][c], blue[r][c]) <= THRESHOLD;
                boolean isGreen = distanceTo(0, 255, 0, red[r][c], green[r][c], blue[r][c]) <= THRESHOLD;
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



