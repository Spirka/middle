package ru.job4j.concurrent;

/**
 * Class ConsoleProgress
 *
 * @author Kseniya Dergunova
 * @since 05.05.2020
 */
public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        try {
            int count = 0;
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(500);
                System.out.print(count % 2 == 0 ? "\r load: " + "- \\ | / -" : "\r load: " + "- / | \\ -");
                count++;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(10000);
        progress.interrupt();
    }
}
