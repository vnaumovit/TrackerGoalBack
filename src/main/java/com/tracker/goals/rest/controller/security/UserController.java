package com.tracker.goals.rest.controller.security;

import com.tracker.goals.exception.UserUsernameExistException;
import com.tracker.goals.model.dto.UserDto;
import com.tracker.goals.service.abst.security.UserService;
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
import java.util.List;

@RestController()
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto user) {
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
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        UserDto user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto userDto) {
        UserDto oldUser = userService.findById(userDto.getId());
        try {
            String oldPassword = oldUser.getPassword();
            if (oldPassword.equals(userDto.getPassword())) {
                userDto.setPassword(oldPassword);
                userService.update(userDto);
            } else {
                userService.save(userDto);
            }
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (UserUsernameExistException u) {
            return ResponseEntity.status(HttpStatus.FOUND).body(null);
        }
    }
}
