package ru.job4j.concurrent.waitnotifynotifyall.consumer_stop;

import java.util.List;

/**
 * Class Producer
 *
 * @author Kseniya Dergunova
 * @since 30.05.2021
 */
public class Producer<T> implements Runnable {

    private final SimpleBlockingQueue<T> queue;
    private final List<T> list;

    public Producer(SimpleBlockingQueue<T> queue, List<T> list) {
        this.queue = queue;
        this.list = list;
    }

    @Override
    public void run() {
        for (T element : list) {
            try{
                queue.offer(element);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}
