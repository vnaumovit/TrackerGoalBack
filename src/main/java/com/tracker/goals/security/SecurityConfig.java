package com.tracker.goals.security;


import com.tracker.goals.exception.RestAuthenticationEntryPoint;
import com.tracker.goals.security.jwt.JWTAuthenticationFilter;
import com.tracker.goals.security.jwt.JWTTokenHelper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@ComponentScan("com.sunday.jewelry")
@RequiredArgsConstructor
public class SecurityConfig {
    private final RestAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomUserDetailsService customUserDetailsService;
    private final JWTTokenHelper jwtTokenHelper;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
            .and()
            .authorizeRequests()
            .antMatchers("/api/account/login", "/api/account/register").permitAll()
            .antMatchers("/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')").anyRequest().authenticated()
            .and().addFilterBefore(
                    new JWTAuthenticationFilter(customUserDetailsService, jwtTokenHelper),
                    UsernamePasswordAuthenticationFilter.class
            );
        http.csrf().disable().cors().and().headers().frameOptions().disable();
        return http.build();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @SneakyThrows
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
