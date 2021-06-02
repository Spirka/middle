package ru.job4j.concurrent.non_block;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

class StackTest {
    @Test
    public void when3PushThen3Poll() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        MatcherAssert.assertThat(stack.poll(), is(3));
        MatcherAssert.assertThat(stack.poll(), is(2));
        MatcherAssert.assertThat(stack.poll(), is(1));
    }

    @Test
    public void when1PushThen1Poll() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        MatcherAssert.assertThat(stack.poll(), is(1));
    }

    @Test
    public void when2PushThen2Poll() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        MatcherAssert.assertThat(stack.poll(), is(2));
        MatcherAssert.assertThat(stack.poll(), is(1));
    }
}
