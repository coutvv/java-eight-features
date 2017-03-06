package ru.coutvv.concurrency.fundamental.buildingblocks.resultcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Есть только один косяк -- несколько потоков могут случайно одновременно
 * вычислять одно и тоже.
 *
 * пример:
 * f(1) нету в кэше
 *
 * A ----> [ compute f(1) ] ----> [ add f(1) in cache ]
 * B -----------> [ compute f(1) ] ----> [ add f(1) in cache ]
 *
 *
 */
public class Memorizer2<A, V> {
    private final Map<A, V> cache = new ConcurrentHashMap<A, V>();
    private final Function<A, V> func;

    public Memorizer2(Function<A, V> func) {
        this.func = func;
    }

    public V compute(A arg) {
        V result = cache.get(arg);
        if(result == null) {
            result = func.apply(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
