package Filters;

import Filters.convolution.BoxBlur;
import Filters.convolution.Convolution;
import Filters.convolution.Emboss;
import Filters.convolution.Sobel;
import Interfaces.PixelFilter;
import core.DImage;

import java.util.ArrayList;

public class DetectionPipeline implements PixelFilter {
    ArrayList<Convolution> filters;

    public DetectionPipeline() {
        filters = new ArrayList<>();
        filters.add(new BoxBlur(3));
        filters.add(new Emboss());
        filters.add(new Sobel());
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
