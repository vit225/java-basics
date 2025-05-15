package org.example.multithreading;

public class SynchronizedBlockCounter implements SiteVisitCounter {

    private final Object lock = new Object();
    private Integer count = 0;

    @Override
    public void incrementVisitCount() {
        synchronized (lock) {
            try {
                Thread.sleep(100);
                count++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int getVisitCount() {
        synchronized (lock) {
            try {
                Thread.sleep(100);
                return count;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}