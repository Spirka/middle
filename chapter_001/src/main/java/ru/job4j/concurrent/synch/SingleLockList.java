package ru.job4j.concurrent.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.List;

/**
 * Class SingleLockList
 *
 * @author Kseniya Dergunova
 * @since 23.05.2020
 */
@ThreadSafe
public class SingleLockList<T> implements Iterable {

    @GuardedBy(value = "this")
    SimpleArrayList<T> simpleArrayList = new SimpleArrayList(16);

    public synchronized void add(T value) {
        simpleArrayList.add(value);
    }

    public synchronized T get(int index) {
        return simpleArrayList.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return this.copy().iterator();
    }

    private synchronized SimpleArrayList<T> copy() {
        SimpleArrayList<T> copy = new SimpleArrayList<>(16);
        for (T element : this.simpleArrayList) {
            copy.add(element);
        }
        return copy;
    }
}
