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

    public SimpleBlockingQueue(int maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
    }

    public synchronized void offer(T value) throws InterruptedException {
            while (this.queue.size() >= this.maxQueueSize) {
                    this.wait();
            }
            this.queue.add(value);
            this.notifyAll();
    }

    synchronized T poll() throws InterruptedException {
        while (this.queue.isEmpty()) {
            this.wait();
        }
        T result = this.queue.poll();
        this.notifyAll();
        return result;
    }

    public synchronized boolean isEmpty() {
        return this.queue.isEmpty();
    }
}
