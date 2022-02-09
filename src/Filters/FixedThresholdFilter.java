package Filters;


import Interfaces.Interactive;
import core.DImage;
import Interfaces.PixelFilter;

public class FixedThresholdFilter implements PixelFilter, Interactive {
    private int threshold;

    public FixedThresholdFilter() {
        threshold = 127;
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] > threshold) {
                    grid[r][c] = 255;
                } else {
                    grid[r][c] = 0;
                }
            }
        }
        img.setPixels(grid);
        return img;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, DImage img) {
        short[][] pixels = img.getBWPixelGrid();
        short val = pixels[mouseY][mouseX];
        System.out.printf("Value at: %d %d is %d%n", mouseX, mouseY, val);

        System.out.println(mouseX + " " + mouseY);
    }

    @Override
    public void keyPressed(char key) {
        System.out.println();
        if (key == '+') {
            threshold += 10;
        } else if (key == '-') {
            threshold -= 10;
        }
        System.out.println("key pressed was: " + key);
        System.out.println("new threshold is: " + threshold);

    }
}

