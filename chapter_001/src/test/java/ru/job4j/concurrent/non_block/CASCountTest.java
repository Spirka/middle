package ru.job4j.concurrent.non_block;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Test
    void replace() {
        String regex = "[0-9]";
        String phone = "+7 (904) 497-97-20";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        String res = "";
        while (matcher.find()) {
            res = res.concat(phone.substring(matcher.start(), matcher.end()));
        }
        System.out.println(res);
    }
}
