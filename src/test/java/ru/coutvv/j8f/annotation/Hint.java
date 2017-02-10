package ru.coutvv.j8f.annotation;

import java.lang.annotation.Repeatable;

/**
 * Created by lomovtsevrs on 10.02.2017.
 */
@Repeatable(Hints.class)
public @interface Hint {
    String value();
}
