package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto add(ItemDto item, Long ownerId);

    ItemDto patch(ItemDto item, Long id, long ownerId);

    ItemDto get(Long id);

    List<ItemDto> getOwnItems(Long ownerId);

    List<ItemDto> search(String text);
}
