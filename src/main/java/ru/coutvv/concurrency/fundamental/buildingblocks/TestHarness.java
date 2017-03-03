package ru.coutvv.concurrency.fundamental.buildingblocks;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by lomovtsevrs on 03.03.2017.
 */
public class TestHarness {

    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for(int i = 0; i < nThreads; i++) {
            Thread t = new Thread(() -> {
                try{
                    startGate.await();
                    try {
                        task.run();
                    } finally {
                        endGate.countDown();
                    }
                } catch (InterruptedException ignored) {}
            });
            t.start();
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) throws InterruptedException {
        long time = new TestHarness().timeTasks(100, () -> {
            int i = Math.round((int)new Random().nextDouble() * 1000);
            System.out.println("oh jeez " + i);
            try {
                Thread.sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("RESULT TIME : " + time/1000000);
    }
}
