package com.tracker.goals.rest.controller.security;

import com.tracker.goals.model.entity.security.User;
import com.tracker.goals.model.dto.UserDto;
import com.tracker.goals.model.entity.security.LoginRequest;
import com.tracker.goals.model.entity.security.UserResponse;
import com.tracker.goals.security.jwt.JWTTokenHelper;
import com.tracker.goals.service.abst.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

import static com.tracker.goals.service.impl.security.UserServiceImpl.USER_NOT_FOUND;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("api/account")
@CrossOrigin
public class AccountController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenHelper jwtTokenHelper;

    @PostMapping("/register")
    public ResponseEntity<?> registration(@Valid @RequestBody UserDto userDto) {
        Optional<User> oldUser = userService.findByEmail(userDto.getEmail());
        if (oldUser.isPresent()) {
            return ResponseEntity.badRequest().body(HttpStatus.FOUND);
        }
        UserDto user = userService.registration(userDto);
        String jwtToken = jwtTokenHelper.generateToken(user.getEmail());
        UserResponse response = new UserResponse(user, jwtToken);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDto user = userService.findByEmailAndPassword(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        String jwtToken = jwtTokenHelper.generateToken(user.getEmail());
        UserResponse response = new UserResponse(user, jwtToken);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/myAccount")
    public ResponseEntity<?> myAccount(Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElseThrow(
                () -> new UsernameNotFoundException(USER_NOT_FOUND)
        );
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
