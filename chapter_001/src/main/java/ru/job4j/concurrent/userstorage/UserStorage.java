package ru.job4j.concurrent.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class UserStorage
 *
 * @author Kseniya Dergunova
 * @since 23.05.2020
 */
@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final Map<Integer, User> users = new HashMap<>();

    public synchronized boolean add(User user) {
        boolean result = false;
        if (!this.users.containsKey(user.getId())) {
            this.users.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    public synchronized boolean update(User user) {
        boolean result = false;
        if (this.users.containsKey(user.getId())) {
            this.users.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    public synchronized boolean delete(User user) {
        return this.users.remove(user.getId(), user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        User sender = this.users.get(fromId);
        User recipient = this.users.get(toId);
        if (this.users.containsKey(fromId) && this.users.containsKey(toId)) {
            if (sender.getAmount() >= amount) {
                sender.setAmount(sender.getAmount() - amount);
                recipient.setAmount(recipient.getAmount() + amount);
                result = true;
            }
        }
        return result;
    }

    public int getCountOfUsers() {
        Set<Integer> keySet = this.users.keySet();
        return keySet.size();
    }

    public User getUser(User user) {
        return this.users.get(user.getId());
    }

    public boolean checkExistUserById(int id) {
        return this.users.containsKey(id);
    }
}
