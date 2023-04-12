package ru.practicum.shareit.user;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {
    Optional<User> add(User user);
    Optional<User> delete(Long id);
    Optional<User> patch(User user, Long id);
    Optional<User> get(Long id);
    Collection<User> users();

}
