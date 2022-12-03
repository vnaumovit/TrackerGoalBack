package com.tracker.goals.mapper;

import com.tracker.goals.model.entity.security.User;
import com.tracker.goals.model.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDto userDto);

    List<UserDto> toDtos(List<User> users);


    UserDto toDto(User user);
}
