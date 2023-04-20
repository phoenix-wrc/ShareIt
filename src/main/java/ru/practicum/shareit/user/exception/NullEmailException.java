package ru.practicum.shareit.user.exception;

public class NullEmailException extends RuntimeException {
    public NullEmailException(String s) {
        super(s);
    }
}
