package ru.coutvv.concurrency.fundamental.sharingobject;

import net.jcip.annotations.NotThreadSafe;

/**
 * Не потокобезопасно потому что доступ к полю через getter и сеттер
 * происходит без синхронизации. Таким образом, состояние склонно
 * выдавать несвежие данные: если один поток вызывает сет, другой
 * вызывая get может и не увидеть изменения
 */
@NotThreadSafe
public class MutableInteger {
    private int value;


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
