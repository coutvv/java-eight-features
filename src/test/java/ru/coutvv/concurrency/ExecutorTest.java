package ru.coutvv.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Исполнитель пытается завершить работу, ожидая завершения запущенных задач в течение
 * определённого времени(5 секунд). По истечении этого времени он останавливается,
 * прерывая все незавершённые задачи.
 */
public class ExecutorTest {

    public static void main(String[] args) {
        ExecutorService ex = Executors.newSingleThreadExecutor();
        ex.submit(() -> {
          String name = Thread.currentThread().getName();
            System.out.println("Hey bitch! " + name);
        });
        try {
            System.out.println("attempt to shutdown executor");
            ex.shutdown();
            ex.awaitTermination(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.err.println("tasks interrupted");
        } finally {
            if(!ex.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            ex.shutdownNow();
            System.out.println("shutdown finished");
        }
    }
}
