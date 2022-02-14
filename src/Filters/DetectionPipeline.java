package Filters;

import Filters.convolution.ColorMask;
import Filters.convolution.Convolution;
import Filters.convolution.clustering.ClusterDebug;
import Filters.convolution.clustering.Kluster;
import Filters.convolution.clustering.Klustering;
import Interfaces.Drawable;
import Interfaces.Interactive;
import Interfaces.PixelFilter;
import Utility.Pair;
import Utility.Serializer;
import core.DImage;
import processing.core.PApplet;

import java.util.ArrayList;

import static Filters.convolution.clustering.Klustering.count;

public class DetectionPipeline implements PixelFilter, Interactive, Drawable {

    private static final int PRINTSIZE = 100;
    ArrayList<Convolution> filters;
    ArrayList<Kluster> center = new ArrayList<>();

    public DetectionPipeline() {
        filters = new ArrayList<>();
        filters.add(new ColorMask());
//        filters.add(new sevenxsevenGaussianBlur());
//        filters.add(new BoxBlur(15));
//        filters.add(new Sobel());
    }

    @Override
    public DImage processImage(DImage img) throws Exception {
        long start = System.currentTimeMillis();
        center.clear();
        img = applyFilters(img);
        Klustering k = new Klustering(3, img.getBWPixelGrid());
        k.kluster();
        Serializer.printAll(k.getClusters());
        center.addAll(k.getClusters());
        ClusterDebug c = new ClusterDebug(k.getClusters());
        img = c.processImage(img);
        System.err.println("Time taken to process image = " + (System.currentTimeMillis() - start) + "ms");
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
    }

    @Override
    public void keyPressed(char key) {
        ColorMask c = (ColorMask) filters.get(0);
        if (key == '+') {
            c.setTHRESHOLD(c.getTHRESHOLD() + 10);
        } else if (key == '-') {
            c.setTHRESHOLD(c.getTHRESHOLD() - 10);
        }
//        System.out.println("new threshold is: " + c.getTHRESHOLD());
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        for (Kluster kluster : center) {
            Pair center = kluster.getCenter();
            window.fill(window.color(0, 0, 0));
            window.ellipse(Float.parseFloat(center.getSecond().toString()), Float.parseFloat(center.getFirst().toString()), 30, 30);
        }

    }
}
