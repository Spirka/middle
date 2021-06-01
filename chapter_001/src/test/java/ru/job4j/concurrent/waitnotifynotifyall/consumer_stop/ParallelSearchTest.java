package ru.job4j.concurrent.waitnotifynotifyall.consumer_stop;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParallelSearchTest {
    @Test
    public void whenReturnElementFromQueue() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        List<Integer> numbers = new LinkedList<>();
        Thread producer = new Thread(()-> {
            try {
                queue.offer(1);
                queue.offer(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        Thread consumer = new Thread(() -> {
            try {
                numbers.add(queue.poll());
                numbers.add(queue.poll());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        producer.start();
        producer.join();
        consumer.start();
        consumer.join();
        assertEquals(numbers, new LinkedList<>(List.of(1,2)));
    }
}
