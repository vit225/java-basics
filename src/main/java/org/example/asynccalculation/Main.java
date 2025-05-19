package org.example.asynccalculation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {
        int quantityThreads = 10;
        DataProcessor dataProcessor = new DataProcessor(quantityThreads);

        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            futures.add(dataProcessor.calculateSumTask(numbers, dataProcessor));
        }

        while (dataProcessor.getActiveTaskCount() > 0) {
            System.out.println("Активных задач: " + dataProcessor.getActiveTaskCount());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Future<Integer> future : futures) {
            try {
                System.out.println("Result: " + future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        dataProcessor.shutdown();
    }
}
