package ru.coutvv.j8f.defaultmeth;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by coutvv on 01.02.2017.
 */
public class TestDefaultMethod {

    @Test
    public void testStaticInterfaceMethod() {
        String log = MyDefaultMethods.fuckOff(" oh jeez");
        System.out.println(log);
        Assert.assertEquals(log, "Fuck off!" + " oh jeez");
    }

    @Test
    public void useDefaultInterfaceMethod() {
        new MyDefaultMethods() {

        }.pissoff();
    }
}
