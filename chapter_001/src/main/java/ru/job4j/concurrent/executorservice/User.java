package ru.job4j.concurrent.executorservice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

/**
 * Class Пользователь.
 *
 * @author Kseniya Dergunova
 * @since 17.07.2021
 */
@Data
@AllArgsConstructor
public class User {

    /**
     * Имя пользователя.
     */
    private String username;

    /**
     * Адрес электронной почты.
     */
    private String email;
}
