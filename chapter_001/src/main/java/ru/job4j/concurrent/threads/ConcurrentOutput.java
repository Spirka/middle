package ru.job4j.concurrent.threads;

/**
 * Class ConcurrentOutput.
 *
 * @author Kseniya Dergunova
 * @since 05.04.2020
 */
public class ConcurrentOutput {
    /**
     * Main method.
     *
     * @param args
     */
    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        another.start();
        second.start();
        System.out.println(Thread.currentThread().getName());
    }
}
