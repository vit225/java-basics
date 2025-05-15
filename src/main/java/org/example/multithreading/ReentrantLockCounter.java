package org.example.multithreading;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements SiteVisitCounter {

    private final ReentrantLock lock = new ReentrantLock();
    private int count;

    @Override
    public void incrementVisitCount() {
        lock.lock();
        try {
            Thread.sleep(100);
            count++;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getVisitCount() {
        lock.lock();
        try {
            Thread.sleep(100);
            return count;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}