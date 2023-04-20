package ru.practicum.shareit.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;

import javax.validation.Valid;
import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@Slf4j
@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService service;

    @Autowired
    public ItemController(ItemService service) {
        this.service = service;
    }

    //    Добавление новой вещи. Будет происходить по эндпойнту POST /items.
//    На вход поступает объект ItemDto. userId в заголовке X-Sharer-User-Id —
//    это идентификатор пользователя, который добавляет вещь. Именно этот пользователь — владелец вещи.
//    Идентификатор владельца будет поступать на вход в каждом из запросов, рассмотренных далее.
    @PostMapping
    public ItemDto createItem(@RequestHeader("X-Sharer-User-Id") long ownerId, @Valid @RequestBody ItemDto item) {
        log.info("Добавляем {}, владельца {}", item, ownerId);
        return service.add(item, ownerId);
    }

//    Редактирование вещи. Эндпойнт PATCH /items/{itemId}.
//    Изменить можно название, описание и статус доступа к аренде.
//    Редактировать вещь может только её владелец.
    @PatchMapping("/{itemId}")
    public ItemDto patchItem(@RequestHeader("X-Sharer-User-Id") long ownerId,
                           @RequestBody ItemDto item,
                           @PathVariable("itemId") long id) {
        log.info("меняем {},владельца {}, ИД итема {}", item, ownerId, id);
        return service.patch(item, id, ownerId);
    }

//    Просмотр информации о конкретной вещи по её идентификатору.
//    Эндпойнт GET /items/{itemId}. Информацию о вещи может просмотреть любой пользователь.
    @GetMapping("/{itemId}")
    public ItemDto getItem(@PathVariable("itemId") long id) {
        log.info("берем по ИД итема {}", id);
        return service.get(id);
    }


//    Просмотр владельцем списка всех его вещей с указанием названия и описания для каждой. Эндпойнт GET /items.
    @GetMapping
    public List<ItemDto> getOwnItems(@RequestHeader("X-Sharer-User-Id") long ownerId) {
        log.info("берем все итемы владельца {}", ownerId);
        return service.getOwnItems(ownerId);
    }


//    Поиск вещи потенциальным арендатором. Пользователь передаёт в строке запроса текст,
//    и система ищет вещи, содержащие этот текст в названии или описании.
//    Происходит по эндпойнту /items/search?text={text}, в text передаётся текст для поиска.
//    Проверьте, что поиск возвращает только доступные для аренды вещи.
    @GetMapping("/search")
    public List<ItemDto> search(@RequestParam String text) {
        log.info("Ищем {} в итемах", text);
        return service.search(text);
    }

}
