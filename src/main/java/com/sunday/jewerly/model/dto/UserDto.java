package com.sunday.jewerly.model.dto;

import com.sunday.jewerly.model.security.Role;
import com.sunday.jewerly.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank(message = "Введите никнейм")
    private String username;
    @Length(min = 4, message = "Пароль должен быть минимум 4 символа")
    private String password;
    @NotBlank(message = "Введите имя")
    private String name;
    @NotBlank(message = "Введите фамилию")
    private String surname;
    @Length(min = 10, message = "Длина телефона должна быть от 11 до 12")
    private String phone;
    @NotNull(message = "Выберите пол")
    private Gender gender;
    @NotNull(message = "Возраст должен быть от 14 до 120")
    private Byte age;
    private Set<Role> roles;
}
