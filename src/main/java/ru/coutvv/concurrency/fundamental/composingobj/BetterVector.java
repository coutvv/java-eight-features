package ru.coutvv.concurrency.fundamental.composingobj;

import net.jcip.annotations.ThreadSafe;

import java.util.Vector;

/**
 * Created by lomovtsevrs on 02.03.2017.
 */
@ThreadSafe
public class BetterVector<E> extends Vector<E> {

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !contains(x);
        if(absent)
            add(x);
        return absent;
    }
}
