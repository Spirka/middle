package ru.job4j.concurrent.immutable;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserCacheTest extends TestCase {

    @Test
    public void testFindAll() {
        UserCache cache = new UserCache();
        cache.add(User.of("first"));
        cache.add(User.of("second"));

        List<User> userList = cache.findAll();
        userList.get(0).setName("firstNew");
        userList.get(0).setName("secondNew");

        assertThat(cache.findAll().get(0).getName(), is("first"));
        assertThat(cache.findAll().get(1).getName(), is("second"));
    }
}
