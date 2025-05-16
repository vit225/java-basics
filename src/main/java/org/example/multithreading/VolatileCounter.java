package org.example.multithreading;

public class VolatileCounter implements SiteVisitCounter {

    private volatile int count;

    @Override
    public void incrementVisitCount() {
        try {
            Thread.sleep(100);
            count++;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getVisitCount() {
        try {
            Thread.sleep(100);
            return count;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}