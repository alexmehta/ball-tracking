package Utility;

public class Utility {

    public static double distanceTo(int r, int g, int b, int otherR, int otherG, int otherB) {
        double dR = Math.abs(r - otherR) * Math.abs(r - otherR);
        double dG = Math.abs(g - otherG) * Math.abs(g - otherG);
        double dB = Math.abs(b - otherB) * Math.abs(b - otherB);
        return Math.sqrt(dR + dG + dB);
    }

}
