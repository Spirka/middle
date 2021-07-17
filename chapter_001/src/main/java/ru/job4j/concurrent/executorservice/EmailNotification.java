package ru.job4j.concurrent.executorservice;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class EmailNotification
 * Сервис для рассылки почты
 *
 * @author Kseniya Dergunova
 * @since 17.07.2021
 */
@Log4j2
public class EmailNotification {

    ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     *  Метод берет данные пользователя и подставляет в шаблон.
     *
     * @param user {@link User} пользователь которому отправляем уведомление.
     */
    public void emailTo(User user) {
        pool.submit(() -> {
            String subject = String.format("Notification %s to email %s", user.getUsername(), user.getEmail());
            String body = String.format("Add a new event to %s", user.getUsername());
            send(subject, body, user.getEmail());
        });
    }

    /**
     * Метод закрывает pool.
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метож отправки почты.
     *
     * @param subject тема.
     * @param body тело письма.
     * @param email адрес почты.
     */
    public void send(String subject, String body, String email) {
    }


}
