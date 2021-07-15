package ru.job4j.concurrent.threads;

/**
 * Class Wget
 *
 * @author Kseniya Dergunova
 * @since 05.04.2020
 */
public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        for (int index = 0; index < 101; index++) {
                            Thread.sleep(1000);
                            System.out.print("\rLoading : " + index  + "%");
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        );
        thread.start();
    }
}
