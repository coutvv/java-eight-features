package ru.coutvv.concurrency.fundamental.threadsafety.playground;

/**
 * Created by lomovtsevrs on 17.02.2017.
 */
public class Atomicity {

    private long time = 6L;
    public long getTime() {return  time;}
    public long addTime(long time) {
        this.time += time;
        return this.time;
    }

    public static void main(String[] args) {
        final Atomicity atom = new Atomicity();
        Thread th1 = new Thread(() -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long before = atom.getTime();
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
