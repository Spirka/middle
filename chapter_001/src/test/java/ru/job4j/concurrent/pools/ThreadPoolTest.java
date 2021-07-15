package ru.job4j.concurrent.pools;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ThreadPoolTest {

    @Test
    public void testStart() throws InterruptedException {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        ThreadPool threadPool = new ThreadPool(10);
        for (int i = 0; i < 10; i++) {
            int num = i;
            Thread thread = new Thread(() -> list.add(num));
            thread.start();
            thread.join();
            threadPool.work(thread);
        }
        threadPool.shutdown();
        assertThat(list.size(), is(10));
    }

    @Test
    public void testSort() {
        List<Integer> list = Arrays.asList(1, 5, 2, 2, 10, 3, 3, 4, 8);
        System.out.println(list.stream().sorted(Comparator.reverseOrder()).distinct().collect(Collectors.toList()));
    }
}
