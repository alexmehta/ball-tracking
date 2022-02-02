package Filters;

import Filters.convolution.*;
import Interfaces.PixelFilter;
import Utility.Pair;
import core.DImage;

import java.util.ArrayList;

public class DetectionPipeline implements PixelFilter {

    private static final int PRINTSIZE = 100;
    ArrayList<Convolution> filters;
    ArrayList<Pair<Integer,Integer>> pairs = new ArrayList<>();

    public DetectionPipeline() {
        filters = new ArrayList<>();
        filters.add(new BoxBlur(3));
        filters.add(new ColorMask());
        filters.add(new Sobel());
    }

    @Override
    public DImage processImage(DImage img) throws Exception {
        img = applyFilters(img);
        pairs.add(FindCenter.count(img));
        if (pairs.size()>=PRINTSIZE){

        }
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
}
