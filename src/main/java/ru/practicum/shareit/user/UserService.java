package ru.practicum.shareit.user;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.exception.UserNotFoundException;

import java.util.Collection;

@Service
public class UserService {
    private final UserRepository storage;

    @Autowired
    public UserService(UserRepository storage) {
        this.storage = storage;
    }

    public User add(@NonNull User user) {
        return storage.add(user).orElseThrow(() -> new UserNotFoundException("что-то не получилось с добавлением"));
    }

    public User delete(@NonNull Long id) {
        return storage.delete(id).orElseThrow(() -> new UserNotFoundException("Не удалось удалить"));

    }

    public User patch(@NonNull User user, Long id) {
        return storage.patch(user, id).orElseThrow(() -> new UserNotFoundException("не удалось обновить пользователя"));

    }

    public Collection<User> users() {
        return storage.users();
    }

    public User getUser(@NonNull Long id) {
        return storage.get(id).orElseThrow(() -> new UserNotFoundException("Такого пользователя нет"));
    }
}
