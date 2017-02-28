package ru.coutvv.concurrency;

import java.util.concurrent.*;

/**
 * Любой вызов future.get блокирует поток до тех пор, пока задача не будет завершена.
 * В наихудшем случае выполнение задачи не завершится никогд, блокируя ваше приложение.
 * Избежать этого можно исопльзуя таймауты.
 */
public class Timeouts {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService es = Executors.newFixedThreadPool(1);
        System.out.println("start!");
        Future<Integer> future = es.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                return 123;
            } catch (InterruptedException e) {
               throw new IllegalArgumentException("task iterrupted", e);
            }

        });
        Integer res = future.get(1,TimeUnit.SECONDS);
        System.out.println("result: " + res);
    }
}
