package ru.coutvv.concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 *  Берём первый выполнившийся результат.
 */
public class InvokeAny {

    static Callable<String> callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newWorkStealingPool();
        List<Callable<String>> callableList = Arrays.asList(
            callable("taks 1" , 2),
            callable("takes2" , 1),
            callable("tak tak tak 3", 3)
        );
        String result = es.invokeAny(callableList);
        System.out.println(result);
    }
}
