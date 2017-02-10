package ru.coutvv.j8f.annotation;

import org.testng.annotations.Test;

/**
 * Created by lomovtsevrs on 10.02.2017.
 */
public class TestAnnotation {

    @Test
    public void test() {
        Hint hint = Person.class.getAnnotation(Hint.class);
        System.out.println(hint);//null

//        Hints hints1 = Person.class.getAnnotation(Hints.class);
//        System.out.println(hints1.value().length);

        Hint[] hints2 = Person.class.getAnnotationsByType(Hint.class); // не работает =(
        System.out.println(hints2.length);


    }
}
