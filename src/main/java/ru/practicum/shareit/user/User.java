package ru.practicum.shareit.user;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
@Builder
public class User {
    private Long id;
    private String name;

    //Должен быть уникальным
    @Email(message = "Почта должно быть почтой")
    private String email;
}
