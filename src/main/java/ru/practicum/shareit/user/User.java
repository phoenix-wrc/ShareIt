package ru.practicum.shareit.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Builder
@Setter
@Getter
public class User {
    private Long id;
    private String name;
    //Должен быть уникальным
    @Email(regexp =
            "^[\\w!#$%&'*+\\-\\/=\\?\\^_`{|}~]+(\\.[\\w!#$%&'*+\\-\\/=\\?\\^_`{|}~]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,6}$")
    private String email;
}
