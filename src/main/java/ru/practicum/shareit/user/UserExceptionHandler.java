package ru.practicum.shareit.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.shareit.user.exception.*;

import javax.validation.ValidationException;
import java.util.Map;

@RestControllerAdvice(assignableTypes = UserController.class)
public class UserExceptionHandler {
    @ExceptionHandler(value = ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> validationException(final ValidationException e) {
        return Map.of("Данные пользователя указаны не верно", e.getMessage());
    }
    @ExceptionHandler(value = InvalidEmailException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> validationException(final InvalidEmailException e) {
        return Map.of("Почтовый адрес указан не верно", e.getMessage());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> notFoundException(final UserNotFoundException e) {
        return Map.of("Пользователь не найден", e.getMessage());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> notFoundException(final HttpMessageNotReadableException e) {
        return Map.of("Переданы некорректные данные", e.getMessage());
    }

    @ExceptionHandler(value = NullEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> notFoundException(final NullEmailException e) {
        return Map.of("Переданы некорректные данные", e.getMessage());
    }
}
