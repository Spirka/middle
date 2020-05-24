package ru.job4j.concurrent.synch;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * Class SimpleArrayList.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 17.07.2018
 */
public class SimpleArrayList<T> implements Iterable<T> {

    private T[] container;
    private int size;
    private int index = 0;
    private int modCount = 0;

    public SimpleArrayList(int size) {
        this.size = size;
        container = (T[]) new Object[size];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            private int indexItr = 0;

            @Override
            public boolean hasNext() {
                return indexItr < index;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[indexItr++];
            }
        };
    }

    public int getSize() {
        return size;
    }

    public void add(T value) {
        container[index++] = value;
        if (index == size) {
            expansionContainer();
            size = container.length;
            modCount++;
        }
    }

    public T get(int index) {
        return container[index];
    }

    private void expansionContainer() {
        T[] newConteiner = (T[]) new Object[(size * 3) / 2 + 1];
        System.arraycopy(container, 0, newConteiner, 0, size);
        container = newConteiner;
    }

    public boolean hasValue(T value) {
        boolean result = false;
        if (index != 0) {
            result = IntStream.range(0, index)
                    .anyMatch(x -> Objects.nonNull(container[x]) ? container[x].equals(value) : container[x] == value);
        }
        return result;
    }

    public T[] toArray() {
        return Arrays.copyOf(container, index);
    }
}
