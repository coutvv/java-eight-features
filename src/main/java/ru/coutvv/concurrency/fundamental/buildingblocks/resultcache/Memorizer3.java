package ru.coutvv.concurrency.fundamental.buildingblocks.resultcache;

import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * Ещё лучше храним будущие значения, вместо самих значений
 *
 * Но всё ещё есть маленькое окно, при котором возможно, что треды будут вычислять паралельно друг от друга
 *
 * f(1) not cached:
 *
 * A ---> [ put Future for f(1) in cache ] ---> [ compute f(1) ] ---> [ set result ]
 * B -----> [ put Future for f(1) in cache ] ---> [ compute f(1) ] ---> [ set result ]
 *
 */
public class Memorizer3<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Function<A, V> c;

    public Memorizer3(Function<A, V> c) {
        this.c = c;
    }

    public V compute(final A arg) {
        Future<V> f = cache.get(arg);
        if(f == null) {
            Callable<V> eval = () -> c.apply(arg);
            FutureTask<V> ft = new FutureTask<V>(eval);
            f = ft;
            cache.put(arg, ft);
            ft.run();
        }
        try {
            return f.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
