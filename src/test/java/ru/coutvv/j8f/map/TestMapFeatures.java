package ru.coutvv.j8f.map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;


/**
 * Created by lomovtsevrs on 10.02.2017.
 */
public class TestMapFeatures {
    Map<Integer, String> map;
    @BeforeMethod
    public void init() {
        map = new HashMap<>();
        for(int i = 0 ; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);//засунуть если пустое
        }
    }

    @Test
    public void test() {
        map.forEach((id, val) -> System.out.println(val));
    }

    @Test
    public void testCompute() {
        final Integer KEY = 3;
        map.computeIfPresent(KEY, (num, val) -> val + num);
        System.out.println(map.get(KEY));
        assertEquals(map.get(KEY), "val33");

        map.computeIfPresent(9, (num, val) -> null);
        assertFalse(map.containsKey(9));

        map.computeIfAbsent(23, num -> num + "val");
        assertTrue(map.containsKey(23));

        map.computeIfAbsent(42, num -> "babam!");
        assertEquals("babam!", map.get(42));
    }

    @Test
    public void testRemoveWithAssociation() {
        map.remove(3, "value");
        assertNotNull(map.get(3));

        map.remove(3, "val3");
        assertNull(map.get(3));

        String res = map.getOrDefault(3, "default");
        assertNotNull(res);
    }

    @Test
    public void testMerge() {
        //если элменет отсутствует мердж создаёт новую пару ключ/значение
        map.merge(80, "shitty", (value, newValue) -> value.concat(newValue));
        assertEquals("shitty", map.get(80));
        //слить 9ый элемент массива с "*updateShitty"
        map.merge(80, "updateShitty", (val, nVal) -> val.concat("*" + nVal));
        assertEquals("shitty*updateShitty", map.get(80));
    }
}
