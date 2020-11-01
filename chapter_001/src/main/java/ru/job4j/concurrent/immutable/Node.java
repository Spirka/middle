package ru.job4j.concurrent.immutable;

/**
 * Immutable Class Node
 *
 * @author Kseniya Dergunova
 * @since 01.11.2020
 */
public class Node<T> {
    private final Node next;
    private final T value;

    public Node(Node next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}
