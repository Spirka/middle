package ru.job4j.concurrent.userstorage;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenAddUserToUserStorageThenAdded() {
        UserStorage userStorage = new UserStorage();
        userStorage.add(new User(1, "Ксения", 100));
        userStorage.add(new User(2, "Ivan", 150));
        int countOfUsers = userStorage.getCountOfUsers();
        assertEquals(2, countOfUsers);
    }

    @Test
    public void whenUpdateUserInUserStorageThenUpdated() {
        UserStorage userStorage = new UserStorage();
        User first = new User(1, "Ксения", 100);
        User second = new User(1, "Ivan", 100);
        userStorage.add(first);
        userStorage.update(second);
        assertEquals(userStorage.getUser(second), second);
    }

    @Test
    public void whenDeleteUserThenDeleted() {
        UserStorage userStorage = new UserStorage();
        User first = new User(1, "Ксения", 100);
        User second = new User(2, "Ivan", 100);
        userStorage.add(first);
        userStorage.add(second);
        userStorage.delete(second);
        assertEquals(userStorage.getUser(first), first);
        assertEquals(1, userStorage.getCountOfUsers());
        assertFalse( userStorage.checkExistUserById(2));
    }

    @Test
    public void transfer() {
        UserStorage userStorage = new UserStorage();
        User first = new User(1, "Ксения", 100);
        User second = new User(2, "Ivan", 100);
        userStorage.add(first);
        userStorage.add(second);
        userStorage.transfer(1, 2, 50);
        assertEquals(50, userStorage.getUser(first).getAmount());
        assertEquals(150, userStorage.getUser(second).getAmount());
    }
}
