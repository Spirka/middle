package ru.job4j.concurrent.waitnotifynotifyall;

import lombok.extern.log4j.Log4j2;

/**
 * Class MultiUser
 *
 * @author Kseniya Dergunova
 * @since 20.12.2020
 */
public class MultiUser {
    public static void main(String[] args) {
        Barrier barrier = new Barrier();
        Thread master = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    barrier.on();
                },
                "Master"
        );
        Thread slave = new Thread(
                () -> {
                    barrier.check();
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Slave"
        );
        master.start();
        slave.start();
    }
}
