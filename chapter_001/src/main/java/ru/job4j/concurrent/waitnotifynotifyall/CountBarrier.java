package ru.job4j.concurrent.waitnotifynotifyall;

/**
 * Class CountBarrier
 *
 * @author Kseniya Dergunova
 * @since 20.12.2020
 */
public class CountBarrier {

    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            this.count = this.count + 1;
            System.out.println("count " + this.count);
            monitor.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (monitor) {
            while (this.total != this.count) {
                monitor.wait();
            }
        }
    }
}
