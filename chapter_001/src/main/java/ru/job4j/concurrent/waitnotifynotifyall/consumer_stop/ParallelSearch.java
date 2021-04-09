package ru.job4j.concurrent.waitnotifynotifyall.consumer_stop;

import net.jcip.annotations.ThreadSafe;

/**
 * Class ParallelSearch
 *
 * @author Kseniya Dergunova
 * @since 04.04.2021
 */
@ThreadSafe
public class ParallelSearch {
    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        final Thread consumer = new Thread(
                () -> {
                    while (queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            e.printStackTrace();
                        }
                    }
                }
        );
        Thread producer =  new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        try {
                            queue.offer(index);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            e.printStackTrace();
                        }
                    }
                }
        );
        consumer.start();
        producer.start();
        producer.join();
        consumer.interrupt();
    }
}
