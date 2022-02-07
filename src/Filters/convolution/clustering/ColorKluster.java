package Filters.convolution.clustering;

import Filters.convolution.Convolution;
import Utility.Point;
import core.DImage;

import java.util.ArrayList;

public class ColorKluster extends Convolution {
    private final static int WHITE = 255;

    //Obj Klusters
    ArrayList<Kluster> klusters;

    public ColorKluster(ArrayList<Kluster> klusters) {
        this.klusters = klusters;
    }

    private static DImage markCluster(Kluster k, DImage img, int r, int g, int b) {
        short[][] gird = img.getBWPixelGrid();
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();


        for (int i = 0; i < k.getPairs().size(); i++) {
            Point p = k.getPairs().get(i);
            Point.swap(p);
            if (gird[(int) p.getxCord()][(int) p.getyCord()] == WHITE) {
                red[(int) p.getxCord()][(int) p.getyCord()] = (short) r;
                green[(int) p.getxCord()][(int) p.getyCord()] = (short) g;
                blue[(int) p.getxCord()][(int) p.getyCord()] = (short) b;


            }
        }
        img.setColorChannels(red, green, blue);
        return img;
    }


    public DImage processImage(DImage img) {
        markCluster(klusters.get(0), img, 255, 0, 0);
        markCluster(klusters.get(1), img, 0, 255, 0);
        markCluster(klusters.get(2), img, 0, 0, 255);

        return img;

    }
}

