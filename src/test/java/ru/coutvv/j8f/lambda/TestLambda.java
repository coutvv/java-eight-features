package ru.coutvv.j8f.lambda;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import ru.coutvv.j8f.defaultmeth.MyConverter;


/**
 * Фишки лямбда-выражений
 * 
 * @author coutvv
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
        MyConverter<String, Integer> mfi = (from) -> Integer.valueOf(from);
        Integer i = mfi.run("324");
        assertEquals(i, (Integer)324);
    }

    @Test
    public void testLinkToStaticMethod() {
        MyConverter<String, Integer> mfi = Integer::valueOf; //static method
        Integer i = mfi.run("324");
        assertEquals(i, (Integer)324);
    }

    @Test
    public void testLinkToObjectMethod() {
        Something something = new Something();
        MyConverter<String, String> mfi = something::startsWith;

        String result = mfi.run("Java");
        assertEquals(result, "J");
    }

    @Test
    public void testLinkToConstractor() {
    	//Компилятор автоматически выбирает конструктор в соответствии с сигнатурой метода create
    	SomethingFactory<Something> someFactory = Something::new; 
    	
    	Something something = someFactory.create("John", "Snow");
    	assertEquals(something.toString(), "John Snow");
    }
    
    @Test
    public void actionFieldOfLambda() {
    	int num = 1;
    	MyConverter<Integer, String> converter = (from) -> {
    		from /= 2; 
    		return String.valueOf(from + num);
    	};
    	String result = converter.run(2);
    	assertEquals(result, "2");
    	
    }
    
    static int outerStaticNum = 0;
    int outerNum = 0;
    
    @Test
    public void testAccessToStaticVar() {
    	MyConverter<Integer, String> conv1 = (from) -> {
    		outerNum = 23;
    		return String.valueOf(from);
    	};
    	
    	MyConverter<Integer, String> conv2 = (from) -> {
    		outerStaticNum = 42;
    		return String.valueOf(from +1);
    	};
    	
    	assertEquals(outerNum, 0);
    	assertEquals(outerStaticNum, 0);
    	
    	String res1 = conv1.run(1);
    	String res2 = conv2.run(3);
    	
    	assertEquals(outerNum, 23);
    	assertEquals(outerStaticNum, 42);
    	
    	assertEquals(res1, "1");
    	assertEquals(res2, "4");
    }
}
