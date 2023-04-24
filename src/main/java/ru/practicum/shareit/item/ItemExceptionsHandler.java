package ru.practicum.shareit.item;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.shareit.user.exception.UserNotFoundException;

import javax.validation.ValidationException;
import java.util.Map;

@RestControllerAdvice(assignableTypes = ItemController.class)
public class ItemExceptionsHandler {
    @ExceptionHandler(value = ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> validationException(final ValidationException e) {
        return Map.of("Данные предмета указаны не верно", e.getMessage());
    }

    @ExceptionHandler(value = NotOwnerAccessError.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> notOwnerException(final NotOwnerAccessError e) {
        return Map.of("Данные предмета принадлежит другому человеку", e.getMessage());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> notOwnerException(final UserNotFoundException e) {
        return Map.of("Чего то не нашлось", e.getMessage());
    }
}
