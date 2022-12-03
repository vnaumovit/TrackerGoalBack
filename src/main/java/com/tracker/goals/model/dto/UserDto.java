package com.tracker.goals.model.dto;

import com.tracker.goals.model.entity.security.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    @NotBlank(message = "Введите имя")
    private String name;
    @NotBlank(message = "Введите фамилию")
    private String surname;
    @Email(message = "Введите корректный email")
    private String email;
    @Length(min = 4, message = "Пароль должен быть минимум 4 символа")
    private String password;
    private Set<Role> roles;
}
