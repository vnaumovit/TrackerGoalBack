package com.sunday.jewerly.mapper;

import com.sunday.jewerly.model.User;
import com.sunday.jewerly.model.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getPhone(),
                userDto.getAge(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getGender(),
                userDto.getRoles()
        );
    }
}