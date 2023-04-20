package ru.practicum.shareit.item;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.exception.UserNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class ItemRepo {
    private final Map<Long, Item> storage = new HashMap<>();
    private long currentId = 1;

    public Optional<ItemDto> add(ItemDto item, Optional<User> user) {
        if (user.isEmpty()) { throw new UserNotFoundException("Пользователя нет");}
        var id = currentId++;
        storage.put(id, ItemMapper.toItemFromDto(id, item, user.get()));
        return Optional.of(ItemMapper.toItemDto(storage.get(id)));
    }

    public Optional<ItemDto> patch(ItemDto item, Long id, Optional<User> user) {
        return null;
    }

    public Optional<ItemDto> getItemById(Long id) {
        return null;
    }

    public List<ItemDto> getOwnItems(Long ownerId) {
        return null;
    }

    public List<ItemDto> search(String text) {
        return null;
    }
}
