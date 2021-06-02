package ru.job4j.concurrent.non_block;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class Cache
 *
 * @author Kseniya Dergunova
 * @since 01.06.2021
 */
public class Cache {

    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        memory.computeIfAbsent(model.getVersion() == memory.get())
    }

    public void delete(Base model) {
        /* TODO impl */
    }
}
