package ru.job4j.concurrent.non_block;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class Cache
 *
 * @author Kseniya Dergunova
 * @since 15.06.2021
 */
public class Cache {

    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (key, value) -> {
            if (value.getVersion() != model.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            Base updated = new Base(key, value.getVersion() + 1);
            updated.setName(model.getName());
            return updated;
        }) != null;
    }

    public void delete(Base model) {
            memory.remove(model.getId());
    }

    public Base get(Integer key) {
        return memory.get(key);
    }
}
