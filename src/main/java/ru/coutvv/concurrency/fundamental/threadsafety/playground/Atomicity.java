package ru.coutvv.concurrency.fundamental.threadsafety.playground;

/**
 * Здесь показывается нарушение атомарности операции
 * То есть первый поток берёт 6 и не успевает выполнить
 * операцию, в то время как второй поток берёт эту же переменную
 * и прибавляет к ней своё время. В момент когда первый поток добавляет
 * своё время, он добавляет не к 6 а к результату из второго потока
 * хотя по логике программы должен добавлять к 6.
 *
 * Created by lomovtsevrs on 17.02.2017.
 */
public class Atomicity {

    private long time = 6L;
    public long getTime() {return  time;}
    public long addTime(long time) {
        this.time += time;
        return this.time;
    }

    public static void lag() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final Atomicity atom = new Atomicity();
        Thread th1 = new Thread(() -> {
            long before = atom.getTime();
            lag();
            long after = atom.addTime(12);
            System.out.println("ко времени = " + before + " добавили 12 получили: " + after + " из потока th1");
        });
        Thread th2 = new Thread(() -> {
            long before = atom.getTime();
            long after = atom.addTime(13);
            System.out.println("ко времени = " + before + " добавили 13 получили: " + after + " из потока th2");
        });
        th1.start();
        th2.start();
    }
}
