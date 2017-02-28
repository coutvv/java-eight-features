package ru.coutvv.concurrency;

import org.testng.annotations.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by lomovtsevrs on 28.02.2017.
 */
public class Tutorial {

    @Test(enabled = false)
    private void exOne() {
        Runnable task  = () -> {
            String thName = Thread.currentThread().getName();
            System.out.println("Hello " + thName);
        };

        task.run();

        Thread th = new Thread(task);
        th.setName("My Thread");
        th.start();

        System.out.println("Done!");
    }

    @Test(enabled = false)
    private void second() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                String name = Thread.currentThread().getName();
                System.out.println("Foo " + name);
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Bar " + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    private void executor() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hey " + threadName);
        });
        TimeUnit.SECONDS.sleep(1);
    }


}
