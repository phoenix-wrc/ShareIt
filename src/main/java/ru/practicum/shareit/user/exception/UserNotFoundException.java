package ru.practicum.shareit.user.exception;

public class UserNotFoundException extends RuntimeException {
    UserNotFoundException(String message) {
        super(message);
    }
}
