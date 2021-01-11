package ru.job4j.concurrent.waitnotifynotifyall;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class SimpleBlockingQueue
 *
 * @author Kseniya Dergunova
 * @since 07.01.2021
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    synchronized void offer(T value) {
        if (value == null) {
            return;
        }
        this.queue.offer(value);
        notify();
    }

    synchronized T poll() {
        while (this.queue.size() == 0) {
            try {
                System.out.println("Ждем когда очередь заполнится...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Очередь заполнилась! Можем брать");
        return this.queue.poll();
    }
}
