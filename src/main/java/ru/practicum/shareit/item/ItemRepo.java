package ru.practicum.shareit.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ItemRepo {
    private final Map<Long, Item> storage = new HashMap<>();
    private long currentId = 1;

    public Optional<ItemDto> add(ItemDto item, User user) {
        var id = currentId++;
        log.info("Получили ДТО {}", item);
        storage.put(id,  ItemMapper.toItemFromDto(id, item, user));
        var out =  Optional.of(ItemMapper.toItemDto(storage.get(id)));
        log.info("положили {}", out);
        return out;
    }

    public Optional<ItemDto> patch(ItemDto itemDto, Long id, Long ownerId) {
        var item = storage.get(id);
        if (!item.getOwner().getId().equals(ownerId)) {
            throw new NotOwnerAccessError("Не владелец не может редактировать");
        }
        if (itemDto.getAvailable() != null) {
            item.setAvailable(itemDto.getAvailable());
        }
        if (itemDto.getName() != null) {
            item.setName(itemDto.getName());
        }
        if (itemDto.getDescription() != null) {
            item.setDescription(itemDto.getDescription());
        }
        if (itemDto.getAvailable() != null) {
            item.setAvailable(itemDto.getAvailable());
        }
        // С полем реквестИд пока ни чего не понятно, так что просто не трогаем
        return Optional.of(ItemMapper.toItemDto(storage.get(id)));
    }

    public Optional<ItemDto> getItemById(Long id) {
        return Optional.of(ItemMapper.toItemDto(storage.get(id)));
    }

    public List<ItemDto> getOwnItems(Long ownerId) {
        return storage.values().stream()
                .filter(item -> item.getOwner().getId().equals(ownerId))
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
    }

    public List<ItemDto> search(String text) {
        return storage.values().stream()
                .filter(Item::getAvailable)
                .filter(item -> item.getName().equalsIgnoreCase(text))
                .filter(item -> item.getDescription().equalsIgnoreCase(text))
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
    }
}
