package ru.coutvv.concurrency.fundamental.sharingobject;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by coutvv on 23.02.2017.
 */
@Immutable
public final class ThreeStooges {
    private final Set<String> stooges = new HashSet<>();

    public ThreeStooges(){
        stooges.add("shitty");
        stooges.add("noshit");
        stooges.add("blabla");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }
}
