package ru.job4j.concurrent.waitnotifynotifyall;

import org.junit.jupiter.api.Test;

class SimpleBlockingQueueTest {

    @Test
    void offer() throws InterruptedException {
        SimpleBlockingQueue<Integer> testQueue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    testQueue.offer(1);
                }, "Producer"
        );
        Thread consumer = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    testQueue.poll();
                }, "Consumer"
        );
        Thread consumer2 = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    testQueue.poll();
                }, "Consumer2"
        );
        producer.start();
        consumer.start();
        consumer2.start();
        consumer.join();
        consumer2.join();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void poll() throws InterruptedException {
        SimpleBlockingQueue<Integer> testQueue = new SimpleBlockingQueue<>();
        Thread consumer = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    System.out.println(testQueue.poll());
                }, "Consumer");
        Thread producer = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    testQueue.offer(1);
                }, "Producer"
        );
        consumer.start();
        producer.start();
        producer.join();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
