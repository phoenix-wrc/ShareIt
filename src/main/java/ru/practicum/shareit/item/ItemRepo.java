package ru.practicum.shareit.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.model.Item;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotBlank;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ItemRepo {
    private final Map<Long, Item> storage = new HashMap<>();
    private long currentId = 1;

    public Optional<ItemDto> add(@Valid Item item) {
        // Я не понимаю как работает валидация хибернейта, вернее как она не работате,
        // единственный выход это валидировать руками
        if (item.getAvailable() == null) {
            throw new ValidationException("нет флага доступности");
        }
        if (item.getDescription() == null) {
            throw new ValidationException("нет описания");
        }
        if (item.getName() == null) {
            throw new ValidationException("нет имени");
        }
        if (item.getName().isEmpty()) {
            throw new ValidationException("имя пустое");
        }
        if (item.getOwner() == null) {
            throw new ValidationException("нет владельца");
        }

        var id = currentId++;
        item.setId(id);
        log.info("Получили новый итем {}", item);

        storage.put(id, item);
        var out = Optional.of(ItemMapper.toItemDto(storage.get(id)));
        log.info("положили {}", out);
        return out;
    }

    public Optional<ItemDto> patch(ItemDto itemDto, long ownerId) {
        var item = storage.get(itemDto.getId());
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
        // С полем реквест Ид пока ни чего не понятно, так что просто не трогаем
        storage.put(item.getId(), item);
        var out = Optional.of(ItemMapper.toItemDto(storage.get(item.getId())));
        log.info("Обновили {}", out.get());
        return out;
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

    public List<ItemDto> search(@NotBlank String text) {
        if (text.isEmpty() && text.isBlank()) {
            return Collections.emptyList();
        }
        log.info("Ишем вхождения в названии и описании текста: {}", text);
        return storage.values().stream()
                .filter(Item::getAvailable)
                .filter(item -> (item.getName().toLowerCase().contains(text.toLowerCase())
                || item.getDescription().toLowerCase().contains(text.toLowerCase())))
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
    }
}
