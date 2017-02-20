package ru.coutvv.concurrency.fundamental.threadsafety.playground;

/**
 * Created by lomovtsevrs on 20.02.2017.
 */
public class JustSoFun {

    private Struct someVariable = new Struct(1);

    public synchronized Struct getSomeVariable() {return someVariable;}


    public static void lag(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        final JustSoFun jsf = new JustSoFun();
        Thread th1 = new Thread(() -> {
            Struct i = jsf.getSomeVariable();
            lag(100);
            i.t += 12;
            lag(1000);
            System.out.println(i.t + " th1");
        });
        Thread th2 = new Thread(() -> {

            Struct i = jsf.getSomeVariable();
            System.out.println("th2 get : " + i.t);
            lag(200);

            System.out.println("th2 set : " + i.t);
            i.t += 5;
            System.out.println(i.t + " th2");
        });
        th1.start();
        th2.start();
    }
    private class Struct {
        public int t;
        Struct(int i) {
            t = i;
        }
    }

}
