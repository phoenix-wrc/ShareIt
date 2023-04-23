package ru.practicum.shareit.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.user.UserService;

import java.util.List;

@Component
public class ItemServiceImpl implements ItemService {
    private final ItemRepo repo;
    private final UserService userRepo;

    @Autowired
    public ItemServiceImpl(ItemRepo repo, UserService userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @Override
    public ItemDto add(ItemDto item, Long ownerId) {
        return repo.add(ItemMapper.toItemFromDto(item, userRepo.getUser(ownerId))).orElseThrow();
    }

    @Override
    public ItemDto patch(ItemDto item, Long id, long ownerId) {
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
    public List<ItemDto> search(String text) {
        return repo.search(text);
    }
}
