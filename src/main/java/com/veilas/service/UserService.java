package com.veilas.service;
import com.veilas.model.User;
import com.veilas.model.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll ();
    Optional<User> findById(long id);
    User save(User user);
    void deleteById(long id);
    Optional<User> findByUsername(String username);
    User passwordCoder(User user);

    User registration(UserDto userDto);

    User findByUsernameAndPassword(String username, String password);
}
