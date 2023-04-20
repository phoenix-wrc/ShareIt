package ru.practicum.shareit.feedback;

import lombok.NonNull;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

public class Feedback {
//    В отзыве можно поблагодарить владельца вещи и
//    подтвердить, что задача выполнена — дрель успешно справилась с бетоном,
//    и картины повешены.
    private Long id;
    @NonNull
    private Item item;
    @NonNull
    private User owner;
    @NonNull
    private User requester;
    @NonNull
    private String description;
}
