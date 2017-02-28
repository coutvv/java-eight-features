package ru.coutvv.concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Исполнители могут принимать список задач на выполнение
 */
public class InvokeAll {
    static int counter = 0;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService ex = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                () -> "task1",
                () -> "task two",
                () -> "task N"
        );

        ex.invokeAll(callables).stream().map(future -> {
            try {
                return future.get() + " fuck offo " + counter++;
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }).forEach(System.out::println);
    }
}
