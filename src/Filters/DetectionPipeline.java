package Filters;

import Filters.convolution.Convolution;
import Interfaces.PixelFilter;
import core.DImage;

import java.util.ArrayList;

public class DetectionPipeline implements PixelFilter {
    ArrayList<Convolution> filters;

    public DetectionPipeline(ArrayList<Convolution> filters) {
       this.filters = filters;
    }

    @Override
    public DImage processImage(DImage img) throws Exception {
        img = applyFilters(img);
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
