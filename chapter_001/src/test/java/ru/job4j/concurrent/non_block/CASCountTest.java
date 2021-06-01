package ru.job4j.concurrent.non_block;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

class CASCountTest {

    @Test
    void whenIncrementOneThenGetOne() {
        CASCount count = new CASCount();
        count.increment();
        MatcherAssert.assertThat(count.get(), is(1));
    }

    @Test
    void whenIncrementTwoThenGetTwo() {
        CASCount count = new CASCount();
        count.increment();
        count.increment();
        MatcherAssert.assertThat(count.get(), is(2));
    }

    @Test
    void whenIncrement3ThenGet3() {
        CASCount count = new CASCount();
        count.increment();
        count.increment();
        count.increment();
        MatcherAssert.assertThat(count.get(), is(3));
    }
}
