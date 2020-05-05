package ru.job4j.concurrent;

/**
 * Class ThreadStop
 *
 * @author Kseniya Dergunova
 * @since 05.05.2020
 */
public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    int count = 0;
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println(count++);
                    }
                }
        );
        thread.start();
        Thread.sleep(1);
        thread.interrupt();
    }
}
