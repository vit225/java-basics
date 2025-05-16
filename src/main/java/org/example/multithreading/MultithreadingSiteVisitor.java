package org.example.multithreading;

import java.util.ArrayList;
import java.util.List;

public class MultithreadingSiteVisitor {

    private final SiteVisitCounter siteVisitCounter;
    private final List<Thread> threads = new ArrayList<>();
    private long startTime;
    private long endTime;

    public MultithreadingSiteVisitor(SiteVisitCounter siteVisitCounter) {
        this.siteVisitCounter = siteVisitCounter;
    }

    public void visitMultithread(int numOfThreads) {
        startTime = System.currentTimeMillis();
        for (int i = 0; i < numOfThreads; i++) {
            Thread thread = new Thread(siteVisitCounter::incrementVisitCount);
            threads.add(thread);
            thread.start();
        }
    }

    public void waitUntilAllVisited() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        endTime = System.currentTimeMillis();
    }

    public double getTotalTimeOfHandling() {
        return (endTime - startTime) / 1000.0;
    }
}