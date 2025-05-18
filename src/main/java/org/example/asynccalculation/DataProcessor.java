package org.example.asynccalculation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class DataProcessor {

    private final ExecutorService executor;
    private final Map<String, Integer> results = new HashMap<>();
    private final AtomicInteger counterTasks = new AtomicInteger(0);

    public DataProcessor(int quantityThreads) {
        this.executor = Executors.newFixedThreadPool(quantityThreads);
    }


    public Future<Integer> calculateSumTask(List<Integer> numbers, DataProcessor dataProcessor) {
        String taskName = "task-" + counterTasks.incrementAndGet();
        CalculateSumTask task = new CalculateSumTask(numbers, taskName, dataProcessor);

        Future<Integer> future = executor.submit(task);

        synchronized (results) {
            try {
                results.put(taskName, future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return future;
    }

    public void decrementTaskCounter() {
        counterTasks.decrementAndGet(); // Уменьшаем счетчик активных задач
    }

    public int getActiveTaskCount() {
        return counterTasks.get();
    }

    public Optional<Integer> getResult(String taskName) {
        synchronized (results) {
            return Optional.ofNullable(results.get(taskName));
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}

