package ru.job4j.concurrent.javamemorymodel;

/**
 * Class DCLSingleton
 *
 * @author Kseniya Dergunova
 * @since 26.10.2020
 */
public final class DCLSingleton {

    private static volatile DCLSingleton inst;

    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }


    private DCLSingleton() {
    }
}
