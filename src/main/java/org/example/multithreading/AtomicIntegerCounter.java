package org.example.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerCounter implements SiteVisitCounter {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void incrementVisitCount() {
        try {
            Thread.sleep(100);
            atomicInteger.incrementAndGet();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getVisitCount() {
        try {
            Thread.sleep(100);
            return atomicInteger.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}