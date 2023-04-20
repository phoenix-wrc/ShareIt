package ru.practicum.shareit.item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ItemDto {
    private String name;
    private String description;
    private Boolean available;
    private Long requestId;
}
