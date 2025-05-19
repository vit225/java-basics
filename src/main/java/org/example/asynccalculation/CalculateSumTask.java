package org.example.asynccalculation;

import java.util.List;
import java.util.concurrent.Callable;

public class CalculateSumTask implements Callable<Integer> {

    private final List<Integer> integerList;
    private final String nameTask;
    private final DataProcessor dataProcessor;

    public CalculateSumTask(List<Integer> integerList, String nameTask, DataProcessor dataProcessor) {
        this.integerList = integerList;
        this.nameTask = nameTask;
        this.dataProcessor = dataProcessor;
    }

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public String getNameTask() {
        return nameTask;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Имя задачи: " + this.nameTask +
                ", Имя текущего потока: " + Thread.currentThread().getName());
        Thread.sleep(400);
        Integer result = 0;
        try {
            for (Integer integer : integerList) {
                result += integer;
            }
        } finally {
            dataProcessor.decrementTaskCounter();
        }

        return result;
    }
}
