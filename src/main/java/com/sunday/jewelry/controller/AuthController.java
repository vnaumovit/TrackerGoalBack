package com.sunday.jewelry.controller;

import com.sunday.jewelry.model.User;
import com.sunday.jewelry.model.dto.UserDto;
import com.sunday.jewelry.service.abst.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@Valid @RequestBody UserDto userDto) {
        Optional<User> oldUser = userService.findByUsername(userDto.getUsername());
        if (oldUser.isPresent()) {
            return ResponseEntity.badRequest().body(HttpStatus.FOUND);
        }
        return ResponseEntity.ok(userService.registration(userDto));
    }
}
