package org.example.multithreading;

public class UnsynchronizedCounter implements SiteVisitCounter {
    private int count;

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