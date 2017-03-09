package ru.coutvv.concurrency.fundamental.threadsafety.reentrancy;


import ru.coutvv.util.LagUtil;

/**
 * Reentrancy означает что блокировка ресурса идёт per-thread
 * а не по вызову. То есть: если тот же самый тред заблокировавший
 * что-то вызовет это чтото, ему можно. А остальные пускай в сторонке
 * подождут
 *
 * Реализация ассоциирует с каждой блокировкой счётчик и тред. Изначально
 * count == 0 => ничего не заблокировано. Каждый раз когда поток входит JVM
 * count++, каждый раз когда выходит count--;
 *
 * Эта штука облегчает писанину ООП-конкаррент кода. Без этого свойства
 * вызов super.doSomeActions(thread); привёл бы к дедлоку
 *
 * @author coutvv
 */
public class Reentrancy {

    public static void main(String[] args) {
        Resource res = new Resource(12L);
        Thread main = new Thread(() -> {
            res.doSomeActions("main");
            System.out.println("main ended");
        }), sec = new Thread(() -> {
            res.doSomeActions("sec");
            System.out.println("sec se");
        }), third = new Thread(() -> {
            res.doSomeActions("third");
            System.out.println("third ended");
        });

        main.start();
        LagUtil.lag(243);
        sec.start();
        third.start();
    }

}


class Resource extends AbstractResource {

    public Resource(Long tt) {
        this.tt = tt;
    }


    @Override
    public synchronized void doSomeActions(String thread) {
        System.out.println(thread);
        LagUtil.lag(1000);
        super.doSomeActions(thread);
    }
}

class AbstractResource {
    protected Long tt;
    public synchronized void doSomeActions(String thread) {
        System.out.println("super " + thread);
        tt = 0L;
    }
}