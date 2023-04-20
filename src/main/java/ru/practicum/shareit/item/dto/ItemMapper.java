package ru.practicum.shareit.item.dto;

import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

public class ItemMapper {
    public static ItemDto toItemDto(Item item) {
        return new ItemDto(
                item.getName(),
                item.getDescription(),
                item.getAvailable(),
                item.getRequest() != null ? item.getRequest().getId() : null
        );
    }

    public static Item toItemFromDto(Long idItem, ItemDto item, User owner) {
        return Item.builder()
                .id(idItem)
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .owner(owner)
                .build();
    }
}
