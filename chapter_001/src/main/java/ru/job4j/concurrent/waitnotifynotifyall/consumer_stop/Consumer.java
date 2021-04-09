package ru.job4j.concurrent.waitnotifynotifyall.consumer_stop;

import java.util.List;

/**
 * Class Consumer
 *
 * @author Kseniya Dergunova
 * @since 30.05.2021
 */
public class Consumer<T> implements Runnable {

    private final SimpleBlockingQueue<T> queue;
    private final List<T> list;

    public Consumer(SimpleBlockingQueue<T> queue, List<T> list) {
        this.queue = queue;
        this.list = list;
    }

    @Override
    public void run() {
        while (!this.queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
            try {
                T element = this.queue.poll();
                list.add(element);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

    }
}
