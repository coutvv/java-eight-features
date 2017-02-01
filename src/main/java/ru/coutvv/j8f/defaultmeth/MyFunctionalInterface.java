package ru.coutvv.j8f.defaultmeth;

/**
 * Нафиг нужен функциональный интерфейс? Он определяет только
 * один абстрактный метод и ну тип функция же! Хз дальше будет понятнее надеюсь
 *
 * аннотация FunctionalInterface говорит что можно определить только 1 метод
 * иначе херак
 *
 * дефолтных методов может быть до чёртиков
 *
 * Created by coutvv on 01.02.2017.
 */
@FunctionalInterface
public interface MyFunctionalInterface<T, F> {

    /**
     * Convert from string to integer
     * @param from
     * @return
     */
    F run(T from);

    default void shit() {
        System.out.println("Some shit stroke");
    }

}
