package ru.job4j.concurrent.pools;

/**
 * Class SearchIndex
 *
 * @author Kseniya Dergunova
 * @since 17.07.2021
 */
public class SearchIndex {

    /**
     * Поиск индекса в массиве.
     *
     * @param array массив.
     * @param object Объект индекс которого нужно найти.
     * @return индекс.
     */
    public static <T> int search(T[] array, int from, int to, T object) {
        for (int i = from; i <= to; i++) {
            if (array[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }
}
