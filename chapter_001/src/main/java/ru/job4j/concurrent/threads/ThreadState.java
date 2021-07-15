package ru.job4j.concurrent.threads;

/**
 * Class ThreadState
 *
 * @author Kseniya Dergunova
 * @since 05.04.2020
 */
public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {
                }
        );
        Thread second = new Thread(
                () -> {
                }
        );
        System.out.println(String.format("Нить 1: %s, %s",
                first.getState().toString(), first.getName()));
        System.out.println(String.format("Нить 2: %s, %s",
                second.getState().toString(), second.getName()));
        first.start();
        second.start();
        while (first.getState() == Thread.State.RUNNABLE
                || second.getState() == Thread.State.RUNNABLE) {
            System.out.println(first.getState());
            System.out.println(second.getState());
        }
        System.out.println(String.format("First is %s, %s",
                first.getState().toString(), first.getName()));
        System.out.println(String.format("Second is %s, %s",
                second.getState().toString(), second.getName()));
        System.out.println(String.format("Work completed %s",
                Thread.currentThread().getName()));
    }
}
