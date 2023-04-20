package ru.practicum.shareit.user;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.exception.InvalidEmailException;
import ru.practicum.shareit.user.exception.NullEmailException;

import javax.validation.ValidationException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class InMemoryUserRepositoryImpl implements UserRepository {
    private final Map<Long, User> users;
    private final Map<Long, String> emails; // Временное решение т.к. в БД это решается проще))
    private Long currentId;

    public InMemoryUserRepositoryImpl() {
        this.users = new HashMap<>();
        emails = new HashMap<>();
        this.currentId = 1L;
    }

    @Override
    public Optional<User> add(@NonNull User user) {
        if (user.getEmail() == null) {
            throw new NullEmailException("Почта у нового пользователя должна быть");
        }
        if (emails.containsValue(user.getEmail())) {
            throw new InvalidEmailException("Такая почта уже используется");
        }
        if (user.getId() == null) {
            user = User.builder()
                    .id(currentId++)
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();
        } else if (users.containsKey(user.getId())) {
            throw new ValidationException("Пользователь с таким ИД уже есть");
        } else {
            throw new ValidationException("Пользователь пришел с ид");
        }
        log.debug("Добаляем пользователя {}", user);
        emails.put(user.getId(), user.getEmail()); // Временное решение т.к. в БД это решается проще))
        users.put(user.getId(), user);
        return Optional.ofNullable(users.get(user.getId()));
    }

    @Override
    public Optional<User> delete(Long id) {
        emails.remove(id);
        return Optional.ofNullable(users.remove(id));
    }

    @Override
    public Optional<User> patch(User user, Long id) {
        return Optional.ofNullable(users.computeIfPresent(id, (key, exsUser) -> {
            if (user.getEmail() != null) {
                if (emails.containsValue(user.getEmail())) {
                    if(!emails.get(id).equals(user.getEmail())) {
                        throw new InvalidEmailException("Да, при обновлении тоже работает");
                    }
                }
                exsUser.setEmail(user.getEmail());
                emails.replace(id, user.getEmail());
            }
            if (user.getName() != null) {
                exsUser.setName(user.getName());
            }
            return exsUser;
        }));
    }

    @Override
    public Optional<User> get(Long id) {
        return Optional.of(users.get(id));
    }

    @Override
    public Collection<User> users() {
        return users.values();
    }
}
