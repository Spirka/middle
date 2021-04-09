package ru.job4j.concurrent.waitnotifynotifyall.consumer_stop;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class SimpleBlockingQueue
 *
 * @author Kseniya Dergunova
 * @since 04.04.2021
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    @GuardedBy("this")
    private final int maxQueueSize;
    private final Object monitor = this;

    public SimpleBlockingQueue(int maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
    }

    public void offer(T value) throws InterruptedException {
        if (value == null) {
            return;
        }
        synchronized (this) {
            while (this.queue.size() >= this.maxQueueSize) {
                System.out.println("Заполняем очередь...");
                monitor.wait();
            }
            this.queue.add(value);
            monitor.notifyAll();
        }
    }

    synchronized T poll() throws InterruptedException {
        T result;
        while (this.queue.isEmpty()) {
            System.out.println("Ждем когда очередь заполнится...");
            monitor.wait();
        }
        System.out.println("Очередь заполнилась! Можем брать");
        result = this.queue.poll();
        monitor.notifyAll();
        return result;
    }

    public synchronized boolean isEmpty() {
        return !this.queue.isEmpty();
    }
}
