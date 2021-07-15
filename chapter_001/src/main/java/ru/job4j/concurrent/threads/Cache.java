package ru.job4j.concurrent.threads;

/**
 * Class Cache
 *
 * @author Kseniya Dergunova
 * @since 21.05.2020
 */
public class Cache {
    private static Cache cache;

    private synchronized static Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }
}
