package ru.practicum.shareit.user;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class User {
    private Long id;
    private String name;

    //Должен быть уникальным
//    @ExtendedEmailValidator
//    @Email(regexp = "^[\\w!#$%&'*+\\-\\/=\\?\\^_`{|}~]+(\\.[\\w!#$%&'*+\\-\\/=\\?\\^_`{|}~]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,6}$")
//    @Email(regexp = ".+[@].+[\\\\.].+")
    @Pattern(
            message = "Ошибка валидации почты, ${validatedValue} не подходящий адрес",
            regexp = "^[\\w!#$%&'*+\\-\\/=\\?\\^_`{|}~]+(\\.[\\w!#$%&'*+\\-\\/=\\?\\^_`{|}~]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,6}$")
    private String email;
}
