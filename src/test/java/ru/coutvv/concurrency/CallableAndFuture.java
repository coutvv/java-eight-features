package ru.coutvv.concurrency;

import java.util.concurrent.*;

/**
 * Created by lomovtsevrs on 28.02.2017.
 */
public class CallableAndFuture {
    public static boolean isRun = true;

    private static Thread th = new Thread(() -> {
        int i = 0;
       for(;isRun;) { i++;
           try {
               TimeUnit.MILLISECONDS.sleep(100);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           System.out.println("exe " + i);
       }
    });

    public static void main(String[] args) {
        Callable task = () -> {
            TimeUnit.SECONDS.sleep(1);
            return 123;
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(task);
        Integer result = null;
        try {
            th.start();
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        isRun = false;
        System.out.println("future done? " + future.isDone());
        System.out.println("result " + result);
        executor.shutdown();
    }
}
