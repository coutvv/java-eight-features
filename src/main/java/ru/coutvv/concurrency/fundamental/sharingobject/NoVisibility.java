package ru.coutvv.concurrency.fundamental.sharingobject;

import ru.coutvv.concurrency.Util;

import java.util.concurrent.ExecutorService;

/**
 * Created by lomovtsevrs on 20.02.2017.
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        public void run() {
            System.out.println("tyr");
            while(!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        Util.lag(200);
        ready = true;
        number = 42;
        System.out.println("shit");
    }
}
