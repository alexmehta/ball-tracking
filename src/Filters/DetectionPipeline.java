package Filters;

import Filters.convolution.BoxBlur;
import Filters.convolution.ColorMask;
import Filters.convolution.Convolution;
import Filters.convolution.FindCenter;
import Filters.convolution.clustering.Klustering;
import Interfaces.Interactive;
import Interfaces.PixelFilter;
import Utility.Pair;
import Utility.Serializer;
import core.DImage;

import java.util.ArrayList;

public class DetectionPipeline implements PixelFilter, Interactive {

    private static final int PRINTSIZE = 100;
    ArrayList<Convolution> filters;
    ArrayList<Pair<Integer, Integer>> pairs = new ArrayList<>();

    public DetectionPipeline() {
        filters = new ArrayList<>();
        filters.add(new ColorMask());
        filters.add(new BoxBlur(3));
//        filters.add(new Sobel());
    }

    @Override
    public DImage processImage(DImage img) throws Exception {
        img = applyFilters(img);
        pairs.add(FindCenter.count(img));
        Klustering k = new Klustering(3, img.getBWPixelGrid());
        k.kluster();
        Serializer.printAll(k.getClusters());
        System.err.println(k.getClusters());
        return img;
    }

    private DImage applyFilters(DImage img) {
        for (Convolution filter : filters) {
            try {
                img = filter.processImage(img);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
        ColorMask c = (ColorMask) filters.get(0);
        if (key == '+') {
            c.setTHRESHOLD(c.getTHRESHOLD() + 10);
        } else if (key == '-') {

            c.setTHRESHOLD(c.getTHRESHOLD() - 10);
        }
        System.out.println("new threshold is: " + c.getTHRESHOLD());

    }
}
