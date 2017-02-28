package ru.coutvv.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by lomovtsevrs on 28.02.2017.
 */
public class ExecutorWithPlanner {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService exs = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            System.out.println("Scheduling: " + System.nanoTime());
        };
        ScheduledFuture<?> future = exs.schedule(task, 3, TimeUnit.SECONDS);
        TimeUnit.MILLISECONDS.sleep(1488L);

        long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
        System.out.println("Remaining Delay: " + remainingDelay);

//        fixedRate();
        scheduleWithFixedDelay();
    }

    /**
     * Интервальный запуск кода(мб переполнение пула потоков(если задача выполняется 2 секунды, а рэйт =1)
     */
    static void fixedRate() {
        ScheduledExecutorService exs = Executors.newScheduledThreadPool(1);
        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());

        int initialDelay = 0;
        int period = 1;
        exs.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
    }

    /**
     * Работает также как и предыдущий, но указанный интервал будет отсчитываться
     * от времени завершения предыдущей задачи.
     *
     * Мы ставим задачу с задержкой в одну секунду между окончанием выполнения
     * задачи и началом следующей.
     */
    static void scheduleWithFixedDelay() {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Scheduling : " + System.nanoTime()/1000000000);
            } catch (InterruptedException e) {
                System.err.println("task interrupted");
            }
        };

        ses.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);
    }
}
