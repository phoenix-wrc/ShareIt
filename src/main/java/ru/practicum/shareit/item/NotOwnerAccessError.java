package ru.practicum.shareit.item;

public class NotOwnerAccessError extends RuntimeException {
    public NotOwnerAccessError(String s) {
        super(s);
    }
}
