package Filters.convolution;

import Interfaces.PixelFilter;
import core.DImage;
import processing.core.PApplet;

import javax.swing.*;

    public class ColorMask implements PixelFilter {
        //threshold -> 10%
        final double THRESHOLD = 44;
        int red;
        int green;
        int blue;
        final int BLACK = 0;
        final int WHITE = 255;

        public ColorMask() {
            String r = JOptionPane.showInputDialog("Enter red(0-255): ");
            String g = JOptionPane.showInputDialog("Enter Green(0-255): ");
            String b = JOptionPane.showInputDialog("Enter Blue(0-255): ");
            this.red = Integer.parseInt(r);
            this.green = Integer.parseInt(g);
            this.blue = Integer.parseInt(b);
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
                    if (distanceTo(this.red, this.green, this.blue, red[r][c], green[r][c], blue[r][c]) <= THRESHOLD) {
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

        @Override
        public void drawOverlay(PApplet window, DImage original, DImage filtered) {

        }

        @Override
        public DImage processImage(DImage img) throws Exception {
            return null;
        }
    }
