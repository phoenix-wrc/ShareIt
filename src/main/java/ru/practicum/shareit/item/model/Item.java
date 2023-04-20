package ru.practicum.shareit.item.model;

import lombok.Data;
import lombok.NonNull;
import ru.practicum.shareit.request.ItemRequest;
import ru.practicum.shareit.user.User;

@Data
public class Item {
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private boolean available;
    private User owner;
    private ItemRequest request;
}
