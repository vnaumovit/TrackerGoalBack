package com.veilas.service;

import com.veilas.exception.UserNotFoundException;
import com.veilas.exception.UserUsernameExistException;
import com.veilas.mapper.UserMapper;
import com.veilas.model.security.Role;
import com.veilas.model.User;
import com.veilas.model.dto.UserDto;
import com.veilas.repository.RoleRepository;
import com.veilas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public User passwordCoder(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    @Override
    @SneakyThrows
    public User registration(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        long ROLE_ID = 2L;
        String roleNotFoundException = String.format("Роль с %s id не найдена", ROLE_ID);
        Role role = roleRepository
                .findById(ROLE_ID)
                .orElseThrow(() -> new RoleNotFoundException(roleNotFoundException));
        user.setRoles(Set.of(role));
        return save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(passwordCoder(user));
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByUsernameAndPassword(String username, String password) {
        User user = findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"));
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}

