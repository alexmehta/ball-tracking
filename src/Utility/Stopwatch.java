package Utility;

public class Stopwatch {
    long start;
    private long time;

    public void start() {
        start = System.currentTimeMillis();
    }

    public long taken() {
        return System.currentTimeMillis() - start;
    }
}
