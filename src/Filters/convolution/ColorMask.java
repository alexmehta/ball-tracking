package Filters.convolution;

import Interfaces.Interactive;
import core.DImage;

import javax.swing.*;

public class ColorMask extends Convolution implements Interactive {
    //threshold -> 10%

    //(255- 110  + , 66 + , 62) distance
     double THRESHOLD = 150;
    final int BLACK = 0;
    final int WHITE = 255;
    final int red = 255;
    final int green = 0;
    final int blue = 0;

    public ColorMask() {
//        String r = JOptionPane.showInputDialog("Enter red(0-255): ");
//        String g = JOptionPane.showInputDialog("Enter Green(0-255): ");
//        String b = JOptionPane.showInputDialog("Enter Blue(0-255): ");
//        this.red = Integer.parseInt(r);
//        this.green = Integer.parseInt(g);
//        this.blue = Integer.parseInt(b);
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
              boolean  isRed =  distanceTo(255,0,0, red[r][c], green[r][c], blue[r][c]) <= THRESHOLD;
              boolean  isBlue =  distanceTo(0,0,255, red[r][c], green[r][c], blue[r][c]) <= THRESHOLD;
              boolean  isGreen =  distanceTo(0,255,0, red[r][c], green[r][c], blue[r][c]) <= THRESHOLD;
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

    @Override
    public void mouseClicked(int mouseX, int mouseY, DImage img) {
        short[][] pixels = img.getBWPixelGrid();
        short val = pixels[mouseY][mouseX];

        System.out.printf("Value at: %d %d is %d%n", mouseX, mouseY, val);
    }

    @Override
    public void keyPressed(char key) {
        System.out.println();
        if (key == '+') {
            THRESHOLD += 10;
        } else if (key == '-') {
            THRESHOLD -= 10;
        }
        System.out.println("key pressed was: " + key);
        System.out.println("new threshold is: " + THRESHOLD);

    }
}


