package ru.job4j.concurrent.non_block;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CacheTest {

    @Test
    public void whenAddThenAdded() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 0);
        Base base3 = new Base(3, 0);
        cache.add(base1);
        cache.add(base2);
        cache.add(base3);
        assertEquals(cache.get(base1.getId()), base1);
        assertEquals(cache.get(base2.getId()), base2);
        assertEquals(cache.get(base3.getId()), base3);
    }

    @Test
    public void whenUpdateThenUpdated() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        cache.add(base1);
        assertEquals(cache.get(base1.getId()), base1);
        Base updatedBase = new Base(1, 0);
        updatedBase.setName("newBase");
        Boolean res = cache.update(updatedBase);
        Base expected = new Base(1, 1,"newBase");
        assertEquals(res, true);
        assertEquals(expected, cache.get(updatedBase.getId()));
    }

    @Test
    public void whenAddThenDelete() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0, "GHJ");
        cache.add(base1);
        assertEquals(cache.get(base1.getId()), base1);
        cache.delete(base1);
        assertNull(cache.get(base1.getId()));
    }

    @Test(expected = OptimisticException.class)
    public void whenGetException() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0, "First");
        Base base2 = new Base(1, 0, "newFirst");
        cache.add(base1);
        cache.update(base2);
        Base expected = new Base(1, 1, "newFirst");
        assertEquals(cache.get(base1.getId()), expected);
        Base base3 = new Base(1, 0, "new");
        cache.update(base3);
        assertEquals(cache.get(base1.getId()), expected);
    }
}
