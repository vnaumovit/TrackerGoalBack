package com.sunday.jewerly.security;

import com.sunday.jewerly.model.security.CustomUserDetails;
import com.sunday.jewerly.model.User;
import com.sunday.jewerly.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service()
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
