package Filters;

import Filters.convolution.*;
import Interfaces.PixelFilter;
import core.DImage;

import java.util.ArrayList;

public class DetectionPipeline implements PixelFilter {
    ArrayList<Convolution> filters;

    public DetectionPipeline() {
        filters = new ArrayList<>();
        filters.add(new BoxBlur(3));
        filters.add(new ColorMask());
        filters.add(new Sobel());
    }

    @Override
    public DImage processImage(DImage img) throws Exception {
        img = applyFilters(img);
        img = FindCenter.count(img);
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
