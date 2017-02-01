package ru.coutvv.j8f.lambda;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.coutvv.j8f.defaultmeth.MyFunctionalInterface;

/**
 * Created by coutvv on 01.02.2017.
 */
public class TestLambda {

    @Test
    public void testThreadLambda() {
        Runnable r = () -> System.out.println("hello");
        Thread th = new Thread(r);
        th.start();
    }

    @Test
    public void testLambdaWithFunctionInterface() {
        MyFunctionalInterface<String, Integer> mfi = (from) -> Integer.valueOf(from);
        Integer i = mfi.run("324");
        Assert.assertEquals(i, (Integer)324);
    }

    @Test
    public void testLinkToStaticMethod() {
        MyFunctionalInterface<String, Integer> mfi = Integer::valueOf; //static method
        Integer i = mfi.run("324");
        Assert.assertEquals(i, (Integer)324);
    }

    @Test
    public void testLinkToObjectMethod() {
        Something something = new Something();
        MyFunctionalInterface<String, String> mfi = something::startsWith;

        String result = mfi.run("Java");
        Assert.assertEquals(result, "J");
    }

    @Test
    public void testLinkToConstractor() {
        // https://habrahabr.ru/post/216431/
        // +
        // http://www.quizful.net/post/new-in-java-8
    }
}
