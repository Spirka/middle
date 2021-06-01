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
            while (queue.size() >= maxQueueSize) {
                monitor.wait();
            }
            queue.add(value);
            monitor.notifyAll();
        }
    }

    synchronized T poll() throws InterruptedException {
        T result;
        while (queue.isEmpty()) {
            monitor.wait();
        }
        result = queue.poll();
        monitor.notifyAll();
        return result;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
