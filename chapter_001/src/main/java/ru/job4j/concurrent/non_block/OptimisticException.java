package ru.job4j.concurrent.non_block;

/**
 * Class OptimisticException
 *
 * @author Kseniya Dergunova
 * @since 01.06.2021
 */
public class OptimisticException extends RuntimeException {

    public OptimisticException(String message) {
        super(message);
    }
}
