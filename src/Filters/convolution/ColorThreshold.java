package Filters.convolution;

import core.DImage;

public class ColorThreshold extends Convolution {
    @Override
    public DImage processImage(DImage img) throws Exception {
        short[][] r = img.getRedChannel();
        short[][] g = img.getGreenChannel();
        short[][] b = img.getBlueChannel();
        HSV[][] val = getHSVvals(r, g, b);
//        val = applyThreshold(val);
        return img;
    }

    private HSV[][] getHSVvals(short[][] r, short[][] g, short[][] b) {
        HSV[][] gr = new HSV[r.length][r[0].length];
        for (int i = 0; i < r.length; i++) {
            short[] shorts = r[i];
            for (int j = 0, shortsLength = shorts.length; j < shortsLength; j++) {
                short red = r[i][j];
                double green = g[i][j] / 255.0;
                double blue = b[i][j] / 255.0;
                double cmax = Math.max(red, Math.max(green, blue));
                double cmin = Math.min(red, Math.min(green, blue));
                double diff = cmax - cmin;
                double h = -1, s;
                if (cmax == cmin) h = 0;
                else if (cmax == r[i][j])
                    h = (60 * ((g[i][j] - b[i][j]) / diff) + 360) % 360;
                else if (cmax == g[i][j])
                    h = (60 * ((b[i][j] - r[i][j]) / diff) + 120) % 360;
                else if (cmax == b[i][j])
                    h = (60 * ((r[i][j] - g[i][j]) / diff) + 240) % 360;
                if (cmax == 0)
                    s = 0;
                else
                    s = (diff / cmax) * 100;
                // compute v
                double v = cmax * 100;
               gr[i][j] = new HSV(h,s,v);
            }
        }
        return gr;
    }

    private static class HSV {
        double H;
        double S;
        double V;

        public HSV(double h, double s, double v) {
            H = h;
            S = s;
            V = v;
        }
    }
}
