package ru.coutvv.concurrency;

/**
 * Created by lomovtsevrs on 20.02.2017.
 */
public class Util {

    public static void lag(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
