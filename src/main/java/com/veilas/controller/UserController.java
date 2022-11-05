package com.veilas.controller;

import com.veilas.exception.ExceptionInfo;
import com.veilas.exception.UserNotFoundException;
import com.veilas.exception.UserUsernameExistException;
import com.veilas.mapper.UserMapper;
import com.veilas.model.User;
import com.veilas.model.dto.UserDto;
import com.veilas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController()
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ExceptionInfo> createUser(@Valid @RequestBody User user) {
        try {
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserUsernameExistException u) {
            throw new UserUsernameExistException("User with username exist");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> pageDelete(@PathVariable("id") long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        User user = userService.findById(id)
                               .orElseThrow(() -> new UserNotFoundException(String.format(
                                       "Пользователь с id = %s не найден",
                                       id
                               )));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/current")
    public ResponseEntity<User> getUserByUsername(Principal principal) {
        User user = userService
                .findByUsername(principal.getName())
                .orElseThrow(() -> new UserNotFoundException(String.format(
                        "Пользователь с никнеймом = %s не найден",
                        principal.getName()
                )));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto userDto) {
        User oldUser = userService.findById(userDto.getId())
                                  .orElseThrow(() -> new UserNotFoundException(String.format(
                                          "Пользователь с id = %s не найден",
                                          userDto.getId()
                                  )));
        try {
            String oldPassword = oldUser.getPassword();
            User user = userMapper.toEntity(userDto);
            if (oldPassword.equals(userDto.getPassword())) {
                System.out.println("TRUE");
                userDto.setPassword(oldPassword);
                userService.save(user);
            } else {
                System.out.println("FALSE");
                userService.save(user);
            }
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserUsernameExistException u) {
            return ResponseEntity.status(HttpStatus.FOUND).body(null);
        }
    }
}
