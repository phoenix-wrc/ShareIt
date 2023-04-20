package ru.practicum.shareit.user;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
//import org.apache.commons.validator.routines.EmailValidator;

@Builder
@Setter
@Getter
public class User {
    private Long id;
    private String name;

    //Должен быть уникальным
//    @ExtendedEmailValidator
    @Email(regexp = "^[\\w!#$%&'*+\\-\\/=\\?\\^_`{|}~]+(\\.[\\w!#$%&'*+\\-\\/=\\?\\^_`{|}~]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,6}$")
//    @Email(regexp = ".+[@].+[\\\\.].+")
    private String email;
}
