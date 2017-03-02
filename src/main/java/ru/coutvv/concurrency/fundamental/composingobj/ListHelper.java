package ru.coutvv.concurrency.fundamental.composingobj;

import net.jcip.annotations.NotThreadSafe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by lomovtsevrs on 02.03.2017.
 */
@NotThreadSafe
public class ListHelper<E> {

    public List<E> list = Collections.synchronizedList(new ArrayList<E>() );

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);
        if(absent)
            list.add(x);
        return absent;
    }
}
