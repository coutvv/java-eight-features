package ru.coutvv.concurrency.fundamental.buildingblocks.resultcache;

import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * Created by lomovtsevrs on 06.03.2017.
 */
public class Memorizer<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Function<A, V> c;

    public Memorizer(Function<A, V> c) {
        this.c = c;
    }

    public V compute(final A arg) {
        Future<V> f = cache.get(arg);
        if(f == null) {
            Callable<V> eval = () -> c.apply(arg);
            FutureTask<V> ft = new FutureTask<V>(eval);
            f = ft;
            cache.putIfAbsent(arg, ft);
            ft.run();
        }
        try {
            return f.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        } catch (CancellationException e) {
            cache.remove(arg,f); //сделано чтобы не загрязнять кэш
            throw e;
        } catch ( ExecutionException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
