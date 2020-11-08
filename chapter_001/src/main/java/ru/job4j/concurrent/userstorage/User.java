package ru.job4j.concurrent.userstorage;

import java.util.Objects;

/**
 * Class User
 *
 * @author Kseniya Dergunova
 * @since 23.05.2020
 */
public class User {

    private final int id;

    private final String name;

    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public User(int id, String name, int amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && amount == user.amount && name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, amount);
    }

    @SuppressWarnings("checkstyle:OperatorWrap")
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
