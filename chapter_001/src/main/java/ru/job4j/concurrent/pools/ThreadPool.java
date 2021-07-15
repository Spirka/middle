package ru.job4j.concurrent.pools;

import ru.job4j.concurrent.waitnotifynotifyall.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Class ThreadPool
 *
 * @author Kseniya Dergunova
 * @since 15.07.2021
 */
public class ThreadPool {
    private final int size = Runtime.getRuntime().availableProcessors();
    private static final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks;

    public ThreadPool(int size) {
        this.tasks = new SimpleBlockingQueue<>(size);
        this.init();
    }

    /**
     * Метод настраивает необходимый размер пула потоков.
     * Инициализация пула должна соответствовать количеству ядер в системе.
     */
    private void init() {
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    tasks.poll();
                }
            });
            threads.add(thread);
            thread.start();
        }
    }
    /**
     * Метод добавляет задачу в очередь.
     * @param job задача
     */
    public void work(Runnable job) {
        this.tasks.offer(job);
    }

    /**
     * Метод метод завершает все запущенные задачи.
     */
    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }
}
