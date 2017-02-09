package ru.coutvv.j8f.stream.parallel;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Демонстрируем скорость работы парарельных потоков
 *
 * Created by lomovtsevrs on 09.02.2017.
 */
public class TestParallelStream {
    final int max = 1000000;
    List<String> values;
    @BeforeMethod
    private void init() {
        values = new ArrayList<String>(max);
        for(int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
    }
    @Test
    public void testParalels() {
        //seq sorting
        System.out.println("[SEQUENTIAL SORT]");
        measureSort(() -> values.stream().sorted().count());

        //parallel sorting
        System.out.println("[PARALLEL SORT]");
        measureSort(() -> values.parallelStream().sorted().count());
    }

    private void measureSort(Supplier<Long> sorting) {
        long t0 = System.nanoTime();
        long count = sorting.get();
        System.out.println(count);
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sort took: %d ms", millis));
    }
}
