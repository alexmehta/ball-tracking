package Filters;

<<<<<<< HEAD
<<<<<<< HEAD
import Filters.convolution.BoxBlur;
import Filters.convolution.ColorMask;
import Filters.convolution.Convolution;
import Filters.convolution.FindCenter;
import Filters.convolution.clustering.ColorKluster;
=======
import Filters.convolution.*;
import Filters.convolution.clustering.ClusterDebug;
>>>>>>> 567d0ad78d4aa562c0a6dac0054e934029f40bf4
=======
import Filters.convolution.ColorMask;
import Filters.convolution.Convolution;
import Filters.convolution.FindCenter;
import Filters.convolution.clustering.ClusterDebug;
import Filters.convolution.clustering.Kluster;
>>>>>>> 553538d25b7cc2899184789aa8c0a3d1596cc98c
import Filters.convolution.clustering.Klustering;
import Interfaces.Drawable;
import Interfaces.Interactive;
import Interfaces.PixelFilter;
import Utility.Point;
import Utility.Serializer;
import core.DImage;
import processing.core.PApplet;

import java.util.ArrayList;

public class DetectionPipeline implements PixelFilter, Interactive, Drawable {

    private static final int PRINTSIZE = 100;
    ArrayList<Convolution> filters;
<<<<<<< HEAD
    ArrayList<Point<Integer, Integer>> pairs = new ArrayList<>();
=======
    ArrayList<Pair<Integer, Integer>> pairs = new ArrayList<>();
    ArrayList<Kluster> center = new ArrayList<>();
>>>>>>> 553538d25b7cc2899184789aa8c0a3d1596cc98c

    public DetectionPipeline() {
        filters = new ArrayList<>();
        filters.add(new ColorMask());
//        filters.add(new sevenxsevenGaussianBlur());
//        filters.add(new BoxBlur(15));
//        filters.add(new Sobel());
    }

    @Override
    public DImage processImage(DImage img) throws Exception {
        center.clear();
        img = applyFilters(img);
        pairs.add(FindCenter.count(img));
        Klustering k = new Klustering(3, img.getBWPixelGrid());
        k.kluster();
        Serializer.printAll(k.getClusters());
        System.err.println(k.getClusters());
<<<<<<< HEAD
        ColorKluster c = new ColorKluster(k.getClusters());
=======
        center.addAll(k.getClusters());
        ClusterDebug c = new ClusterDebug(k.getClusters());
>>>>>>> 553538d25b7cc2899184789aa8c0a3d1596cc98c
        img = c.processImage(img);
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

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        for (Kluster kluster : center) {
            Pair center = kluster.getCenter();
            window.fill(window.color(0, 0, 0));
            window.ellipse( Float.parseFloat(center.getSecond().toString()),  Float.parseFloat(center.getFirst().toString()), 30, 30);
        }

    }
}
