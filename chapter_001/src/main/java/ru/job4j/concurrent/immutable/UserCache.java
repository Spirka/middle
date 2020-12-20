package ru.job4j.concurrent.immutable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class UserCache
 *
 * @author Kseniya Dergunova
 * @since 08.11.2020
 */
public class UserCache {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public void add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getName()));
    }

    public User findById(int id) {
        return User.of(users.get(id).getName());
    }

    public List<User> findAll() {
        List<User> usersNew = new ArrayList<>();
        this.users.forEach(
                (key, value) -> usersNew.add(User.of(value.getName())));
        return usersNew;
    }
}
