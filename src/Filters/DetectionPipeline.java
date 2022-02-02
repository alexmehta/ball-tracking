package Filters;

import Filters.convolution.BoxBlur;
import Filters.convolution.ColorMask;
import Filters.convolution.Convolution;
import Filters.convolution.FindCenter;
import Interfaces.PixelFilter;
import Utility.Pair;
import Utility.Serializer;
import core.DImage;

import java.util.ArrayList;

public class DetectionPipeline implements PixelFilter {

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
        System.out.println(pairs.get(pairs.size() - 1));
        if (pairs.size() == PRINTSIZE) {
            Serializer.printAll(pairs);
        } else if (pairs.size() > PRINTSIZE) {
            Serializer.print(pairs.get(pairs.size() - 1));
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
