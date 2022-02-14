package Filters;

import Filters.clustering.ClusterDebug;
import Filters.clustering.Kluster;
import Filters.clustering.Klustering;
import Filters.convolution.ColorMask;
import Filters.convolution.Convolution;
import Interfaces.Drawable;
import Interfaces.Interactive;
import Interfaces.PixelFilter;
import Utility.Pair;
import Utility.Serializer;
import Utility.Stopwatch;
import core.DImage;
import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Runs Filter Classes
 * <p>Runs Filters and Klusters</p>
 **/
public class DetectionPipeline implements PixelFilter, Interactive, Drawable {

    ArrayList<Convolution> filters;
    ArrayList<Kluster> center = new ArrayList<>();
    Stopwatch stopwatch = new Stopwatch();

    public DetectionPipeline() {
        filters = new ArrayList<>();
        filters.add(new ColorMask());
    }

    @Override
    public DImage processImage(DImage img) {
        stopwatch.start();
        img = applyFilters(img);
        Klustering k = new Klustering(3, img.getBWPixelGrid());
        k.kluster();
        Serializer.printAll(k.getClusters());
        center = new ArrayList<>(k.getClusters());
        ClusterDebug c = new ClusterDebug(k.getClusters());
        img = c.processImage(img);
        System.out.println(stopwatch.taken() + "ms");
        return img;
    }

    /**
     * @param img insert a {@link core.DImage}
     **/
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
        System.out.println("new threshold is: " + c.getTHRESHOLD());
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        for (Kluster kluster : center) {
            Pair<Integer, Integer> center = kluster.getCenter();
            window.fill(window.color(0, 0, 0));
            window.ellipse(Float.parseFloat(center.getSecond().toString()), Float.parseFloat(center.getFirst().toString()), 30, 30);
        }

    }
}
