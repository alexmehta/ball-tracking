package Utility;

public class Stopwatch {
    private long start;

    public void start() {
        start = System.currentTimeMillis();
    }

    public long taken() {
        return System.currentTimeMillis() - start;
    }
}
