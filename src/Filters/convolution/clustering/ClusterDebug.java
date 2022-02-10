package Filters.convolution.clustering;

import Filters.convolution.Convolution;
import Utility.Pair;
import core.DImage;

import java.util.ArrayList;

public class ClusterDebug extends Convolution{
    ArrayList<Kluster> klusters;
    public ClusterDebug(){

    }

    public ClusterDebug(ArrayList<Kluster> klusters) {
        this.klusters = klusters;
    }

    private static void markCluster(Kluster k, DImage img, int r, int g, int b) {
       short [][] gird = img.getBWPixelGrid();
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();


        for (int i = 0; i <k.getPairs().size() ; i++) {
            Pair<Integer, Integer> p = k.getPairs().get(i);
            Pair.swap(p);
            if (gird[(int) p.getSecond()][(int) p.getFirst()]==255){
                red[(int) p.getSecond()][(int)p.getFirst()] = (short) r;
                green[(int) p.getSecond()][(int)p.getFirst()] = (short) g;
                blue[(int) p.getSecond()][(int)p.getFirst()] = (short) b;


            }
        }
        img.setColorChannels(red,green,blue);
    }


    public DImage processImage(DImage img)  {
        markCluster(klusters.get(0),img,255,0,0);
        markCluster(klusters.get(1),img,0,255,0);
        markCluster(klusters.get(2),img,0,0,255);

        return img;

    }
}

