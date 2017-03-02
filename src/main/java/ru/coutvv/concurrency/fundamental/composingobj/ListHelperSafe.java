package ru.coutvv.concurrency.fundamental.composingobj;

import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lomovtsevrs on 02.03.2017.
 */
@ThreadSafe
public class ListHelperSafe<E> {

    public List<E> list = Collections.synchronizedList(new ArrayList<E>() );

    public  boolean putIfAbsent(E x) {
        synchronized (list) {
            boolean absent = !list.contains(x);
            if(absent)
                list.add(x);
            return absent;
        }
    }

}
