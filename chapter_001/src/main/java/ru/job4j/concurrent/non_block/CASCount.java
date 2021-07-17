package ru.job4j.concurrent.non_block;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Class CASCount
 *
 * @author Kseniya Dergunova
 * @since 01.06.2021
 */
@ThreadSafe
public class CASCount {

    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASCount() {
        count.set(0);
    }

    public void increment() {
        Integer lastValue;
        do {
            lastValue = count.get();
        } while (!count.compareAndSet(lastValue, lastValue + 1));
    }

    public int get() {
        return count.get();
    }
}
