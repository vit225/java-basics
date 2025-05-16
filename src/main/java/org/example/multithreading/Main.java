package org.example.multithreading;

public class Main {
    public static void main(String[] args) {

        UnsynchronizedCounter unsynchronizedCounter = new UnsynchronizedCounter();
        VolatileCounter volatileCounter = new VolatileCounter();
        ReentrantLockCounter reentrantLockCounter = new ReentrantLockCounter();
        SynchronizedBlockCounter synchronizedBlockCounter = new SynchronizedBlockCounter();
        AtomicIntegerCounter atomicIntegerCounter = new AtomicIntegerCounter();

        MultithreadingSiteVisitor unsynchronizedSiteVisitor = new MultithreadingSiteVisitor(unsynchronizedCounter);
        MultithreadingSiteVisitor volatileSiteVisitor = new MultithreadingSiteVisitor(volatileCounter);
        MultithreadingSiteVisitor synchronizedSiteVisitor = new MultithreadingSiteVisitor(synchronizedBlockCounter);
        MultithreadingSiteVisitor atomicSiteVisitor = new MultithreadingSiteVisitor(atomicIntegerCounter);
        MultithreadingSiteVisitor reentrantLockSiteVisitor = new MultithreadingSiteVisitor(reentrantLockCounter);

        System.out.println("Тестирование unsynchronizedSiteVisitor");
        unsynchronizedSiteVisitor.visitMultithread(100);
        unsynchronizedSiteVisitor.waitUntilAllVisited();
        System.out.println("count: " + unsynchronizedCounter.getVisitCount());
        System.out.println("totalTime: " + unsynchronizedSiteVisitor.getTotalTimeOfHandling());

        System.out.println("Тестирование volatileSiteVisitor");
        volatileSiteVisitor.visitMultithread(100);
        volatileSiteVisitor.waitUntilAllVisited();
        System.out.println("count: " + volatileCounter.getVisitCount());
        System.out.println("totalTime: " + volatileSiteVisitor.getTotalTimeOfHandling());

        System.out.println("Тестирование synchronizedSiteVisitor");
        synchronizedSiteVisitor.visitMultithread(100);
        synchronizedSiteVisitor.waitUntilAllVisited();
        System.out.println("count: " + synchronizedBlockCounter.getVisitCount());
        System.out.println("totalTime: " + synchronizedSiteVisitor.getTotalTimeOfHandling());

        System.out.println("Тестирование atomicSiteVisitor");
        atomicSiteVisitor.visitMultithread(100);
        atomicSiteVisitor.waitUntilAllVisited();
        System.out.println("count: " + atomicIntegerCounter.getVisitCount());
        System.out.println("totalTime: " + atomicSiteVisitor.getTotalTimeOfHandling());

        System.out.println("Тестирование reentrantLockSiteVisitor");
        reentrantLockSiteVisitor.visitMultithread(100);
        reentrantLockSiteVisitor.waitUntilAllVisited();
        System.out.println("count: " + reentrantLockCounter.getVisitCount());
        System.out.println("totalTime: " + reentrantLockSiteVisitor.getTotalTimeOfHandling());
    }
}