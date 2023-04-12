package ru.practicum.shareit.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {
    private final UserRepository storage;

    @Autowired
    public UserService(UserRepository storage) {
        this.storage = storage;
    }

    public User add(User user) {
        return storage.add(user).orElseThrow();
    }

    public User delete(Long id) {
        return storage.delete(id).orElseThrow();

    }

    public User patch(User user, Long id) {
        return storage.patch(user, id).orElseThrow();

    }

    public Collection<User> users() {
        return storage.users();
    }

    public User getUser(Long id) {
        return storage.get(id).orElseThrow();
    }
}
