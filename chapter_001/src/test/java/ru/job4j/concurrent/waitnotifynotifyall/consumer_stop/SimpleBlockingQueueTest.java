package ru.job4j.concurrent.waitnotifynotifyall.consumer_stop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SimpleBlockingQueueTest {

    @Test
    public void test() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        List<Integer> listIn = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        List<Integer> listOut = new ArrayList<>();
        Producer<Integer> producer = new Producer<>(queue, listIn);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Consumer<Integer> consumer = new Consumer<>(queue, listOut);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
        producerThread.join();
        consumerThread.interrupt();
        consumerThread.join();
        Assertions.assertEquals(listOut, listIn);
    }
}
