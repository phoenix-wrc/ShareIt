package ru.practicum.shareit.item;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserService;

import javax.validation.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Component
@Slf4j
public class ItemServiceImpl implements ItemService {
    private final ItemRepo repo;
    private final UserService userRepo;

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Autowired
    public ItemServiceImpl(ItemRepo repo, UserService userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @Override
    public ItemDto add(ItemDto item, Long ownerId) {
        // Я не понимаю как работает валидация хибернейта, вернее как она не работате,
        // единственный выход это валидировать почти руками
        Item i = ItemMapper.toItemFromDto(item, userRepo.getUser(ownerId));
        for (ConstraintViolation<Item> violation : validator.validate(i)) {
            log.error(violation.getMessage());
            throw new ValidationException(violation.getMessage());
        }
        return repo.add(i).orElseThrow();
    }

    @Override
    public ItemDto patch(ItemDto item, Long id, Long ownerId) {
        item.setId(id);
        return repo.patch(item, ownerId).orElseThrow();
    }

    @Override
    public ItemDto get(Long id) {
        return repo.getItemById(id).orElseThrow();
    }

    @Override
    public List<ItemDto> getOwnItems(Long ownerId) {
        return repo.getOwnItems(ownerId);
    }

    @Override
    public List<ItemDto> search(@NotBlank @NonNull @NotEmpty String text) {
        return repo.search(text);
    }
}
