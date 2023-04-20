package ru.practicum.shareit.item;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

@Component
public class ItemServiceImpl implements ItemService {


    @Override
    public ItemDto add(ItemDto item, Long ownerId) {
        return null;
    }

    @Override
    public ItemDto patch(ItemDto item, Long id, long ownerId) {
        return null;
    }

    @Override
    public ItemDto get(Long id) {
        return null;
    }

    @Override
    public List<ItemDto> getOwnItems(Long ownerId) {
        return null;
    }

    @Override
    public List<ItemDto> search(String text) {
        return null;
    }
}
