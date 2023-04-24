package ru.practicum.shareit.item.dto;

import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

import javax.validation.Valid;

public class ItemMapper {
    public static ItemDto toItemDto(@Valid Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getAvailable(),
                item.getRequest() != null ? item.getRequest().getId() : null
        );
    }

    public static Item toItemFromDto(ItemDto item, User owner) {
        return new Item(null,
                item.getName(),
                item.getDescription(),
                item.getAvailable(),
                owner,
                null);
    }
}
