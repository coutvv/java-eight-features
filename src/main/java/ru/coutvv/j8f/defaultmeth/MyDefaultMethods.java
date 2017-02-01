package ru.coutvv.j8f.defaultmeth;

import java.util.Comparator;

/**
 * Created by coutvv on 01.02.2017.
 */
public interface MyDefaultMethods {

    public static String fuckOff(String fuck) {
        return "Fuck off!" + fuck;
    }

    default void pissoff() {
        System.out.println("don't give up your dream!");
    }

}
