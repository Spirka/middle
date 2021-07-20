package ru.job4j.concurrent.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Class ParallelSearchIndex
 *
 * @author Kseniya Dergunova
 * @since 18.07.2021
 */
public class ParallelSearchIndex<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final int from;
    private final int to;
    private final T objectToSearch;

    public ParallelSearchIndex(T[] array, int from, int to, T objectToSearch) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.objectToSearch = objectToSearch;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return SearchIndex.search(array, from, to, objectToSearch);
        }
        int mid = (from + to) / 2;
        ParallelSearchIndex<T> leftSearch = new ParallelSearchIndex<>(array, from, mid, objectToSearch);
        ParallelSearchIndex<T> rightSearch = new ParallelSearchIndex<>(array, mid + 1, to, objectToSearch);
        leftSearch.fork();
        rightSearch.fork();
        int indexFirst = leftSearch.join();
        int indexSecond = rightSearch.join();
        return indexFirst != -1 ? indexFirst : indexSecond;
    }

    public static <T> Integer search(T[] array, T object) {
        return new ForkJoinPool()
                .invoke(new ParallelSearchIndex<>(array, 0, array.length - 1, object));
    }
}
