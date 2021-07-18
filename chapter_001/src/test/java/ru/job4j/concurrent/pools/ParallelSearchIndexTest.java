package ru.job4j.concurrent.pools;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParallelSearchIndexTest {

    @Test
    public void testSearchWhenFourElements() {
        List<String> names = Arrays.asList("Masha", "Vasya", "Fedor", "Marina");
        assertEquals(2, (int) ParallelSearchIndex.search(names.toArray(), "Fedor"));
    }

    @Test
    public void testSearchWhen11Elements() {
        List<String> names = Arrays.asList("Masha", "Vasya", "Fedor", "Marina", "Marina1", "Marina2", "Marina3", "Marina4", "Marina5", "Marina6", "Marina7");
        assertEquals(6, (int) ParallelSearchIndex.search(names.toArray(), "Marina3"));
        assertEquals(0, (int) ParallelSearchIndex.search(names.toArray(), "Masha"));
    }

    @Test
    public void testSearchWhenFifteenthElements() {
        Integer[] numbers = new Integer[20];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = 100 + i;
        }
        System.out.println(Arrays.toString(numbers));
        Integer index = ParallelSearchIndex.search(numbers, 110);
        assertEquals(10, (int) ParallelSearchIndex.search(numbers, 110));
    }
}
