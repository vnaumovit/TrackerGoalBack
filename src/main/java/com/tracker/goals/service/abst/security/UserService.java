package com.tracker.goals.service.abst.security;
import com.tracker.goals.model.entity.security.User;
import com.tracker.goals.model.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAll ();
    UserDto findById(long id);

    UserDto save(UserDto user);
    UserDto update(UserDto user);
    void deleteById(long id);
    Optional<User> findByEmail(String email);
    UserDto passwordCoder(UserDto userDto);

    UserDto registration(UserDto userDto);

    UserDto findByEmailAndPassword(String username, String password);
}
