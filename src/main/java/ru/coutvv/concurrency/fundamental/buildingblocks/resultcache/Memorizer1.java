package ru.coutvv.concurrency.fundamental.buildingblocks.resultcache;

import net.jcip.annotations.GuardedBy;

import java.lang.management.ThreadInfo;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Пример создания кэширующей хрени :\ чот неочень
 *
 * неочень по следующей причине, сидели три потока:
 *
 * А -->[ compute f(1) ]
 * B ------------------->[ compute f(2) ]
 * C------------------------------------->[ compute f(1) ]
 *
 * вот такая производительность херовая
 */
public class Memorizer1<A, V> {
    @GuardedBy("this")
    private final Map<A, V> cache = new HashMap<A, V>();
    private final Function<A,V> computable;

    public Memorizer1(Function<A,V> c) {
        this.computable = c;
    }

    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if(result == null) {
            result = computable.apply(arg);
            cache.put(arg, result);
        }
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        Memorizer1<Double, Double> cacheSystem = new Memorizer1<>((arg) -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return arg*12;
        });
        System.out.println(cacheSystem.compute(12.0));
        System.out.println(cacheSystem.compute(13.0));
        System.out.println(cacheSystem.compute(12.0));
        System.out.println(cacheSystem.compute(13.20));


    }
}
