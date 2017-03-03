package ru.coutvv.concurrency.fundamental.buildingblocks;

import net.jcip.annotations.GuardedBy;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Дерьмо
 */
public class HiddenIterator {
    @GuardedBy("this")
    private final Set<Integer> set = new HashSet<>();

    public synchronized void add(Integer i) {set.add(i);}
    public synchronized void remove(Integer i) {set.remove(i);}

    public void addTenThings() {
        Random r = new Random();
        for(int i = 0; i < 10; i++) {
            add(r.nextInt());
        }
        System.out.println("DEBUG : added ten elemnts to " + set);//problem here! hidden iteration!!!
    }

    public static void main(String[] args) {
        HiddenIterator hi = new HiddenIterator();
        hi.addTenThings();
    }
}
