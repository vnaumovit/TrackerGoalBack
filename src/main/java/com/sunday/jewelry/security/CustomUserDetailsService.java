package com.sunday.jewelry.security;

import com.sunday.jewelry.model.security.CustomUserDetails;
import com.sunday.jewelry.model.User;
import com.sunday.jewelry.repository.UserRepository;
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
    public CustomUserDetails loadUserByUsername(String s){
        User user = userRepository
                .findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return CustomUserDetails.fromUserToCustomerUserDetails(user);
    }
}
