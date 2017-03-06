package ru.coutvv.concurrency.fundamental.buildingblocks;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Created by lomovtsevrs on 06.03.2017.
 */
public class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore sem;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        sem = new Semaphore(bound);
    }

    public boolean add (T o) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            if(!wasAdded)
                sem.release();
        }
    }

    public boolean remove(Object o ) {
        boolean wasRemoved = set.remove(o);
        if(wasRemoved)
            sem.release();
        return wasRemoved;
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedHashSet<String> set = new BoundedHashSet<>(12);
        String s;
        for(int i =0 ; i < 12; i++) {
            s = "fuckyou" + i;
            set.add(s);
        }
        System.out.println("all is norm");
        set.add("no one");
        System.out.println("we will never come to here!!!");
    }
}
