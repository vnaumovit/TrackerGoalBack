package com.tracker.goals.service.impl.security;

import com.tracker.goals.exception.UserNotFoundException;
import com.tracker.goals.mapper.UserMapper;
import com.tracker.goals.model.entity.security.User;
import com.tracker.goals.model.dto.UserDto;
import com.tracker.goals.model.entity.security.Role;
import com.tracker.goals.repository.security.RoleRepository;
import com.tracker.goals.repository.security.UserRepository;
import com.tracker.goals.service.abst.security.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    public static final String USER_NOT_FOUND = "Пользователь не найден";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDto passwordCoder(UserDto userDto) {
        log.debug("Encode password userDto {}", userDto.getEmail());
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userDto;
    }

    @Override
    @SneakyThrows
    public UserDto registration(UserDto userDto) {
        int ROLE_ID = 1;
        String roleNotFoundException = String.format("Роль с %s id не найдена", ROLE_ID);
        Role role = roleRepository
                .findById(ROLE_ID)
                .orElseThrow(() -> new RoleNotFoundException(roleNotFoundException));
        userDto.setRoles(Set.of(role));
        log.info("Registration new user {}", userDto.getEmail());
        return save(userDto);
    }

    @Override
    public List<UserDto> findAll() {
        return userMapper.toDtos(userRepository.findAll());
    }

    @Override
    public UserDto findById(long id) {
        User user = userRepository.findById(id)
                                  .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto save(UserDto userDto) {
        userDto = passwordCoder(userDto);
        log.debug("Save or update userDto {}", userDto.getEmail());
        User user = userMapper.toEntity(userDto);
        userRepository.save(user);
        userDto.setId(user.getId());
        return userDto;
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        userRepository.save(user);
        return userDto;
    }

    @Override
    public void deleteById(long id) {
        log.info("Delete user with id {}", id);
        userRepository.deleteById(id);
    }

    public Optional<User> findByEmail(String email) throws RuntimeException{
        log.info("Find user by email {}", email);
        return userRepository.findByEmail(email);
    }

    public UserDto findByEmailAndPassword(String email, String password) {
        User user = findByEmail(email).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        UserDto userDto = userMapper.toDto(user);
        log.info("Find user with email and password {}", email);
        if (passwordEncoder.matches(password, userDto.getPassword())) {
            return userDto;
        }
        return null;
    }
}

