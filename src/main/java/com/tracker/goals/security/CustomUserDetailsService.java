package com.tracker.goals.security;

import com.tracker.goals.model.entity.security.CustomUserDetails;
import com.tracker.goals.model.entity.security.User;
import com.tracker.goals.repository.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("customerDetailsServiceImpl")
@Transactional()
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @SneakyThrows
    public CustomUserDetails loadUserByUsername(String email){
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        return CustomUserDetails.fromUserToCustomerUserDetails(user);
    }
}
