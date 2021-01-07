package ru.job4j.concurrent.waitnotifynotifyall;


import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class MultiUser
 *
 * @author Kseniya Dergunova
 * @since 20.12.2020
 */
public class CountMultiUser {

    public static void main(String[] args) {
        CountBarrier countBarrier = new CountBarrier(1);
        Thread master = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    try {
                        countBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                "Master"
        );
        Thread slave = new Thread(
                () -> {
                    countBarrier.count();
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Slave"
        );
        master.start();
        slave.start();
    }
}
