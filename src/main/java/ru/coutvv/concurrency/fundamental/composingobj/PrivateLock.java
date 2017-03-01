package ru.coutvv.concurrency.fundamental.composingobj;

import net.jcip.annotations.GuardedBy;

import java.util.Objects;

/**
 * Блокировка объекта другим объектом(щито?)
 */
public class PrivateLock {

    private final Object myLock = new Object();

    @GuardedBy("myLock")
    Widget widget;

    void someMethod() {
        synchronized (myLock) {
            //ДОступ или модификация состояния виджета
        }
    }

    private class Widget {};
}


