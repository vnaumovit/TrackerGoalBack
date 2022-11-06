package com.sunday.jewelry.security;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@ComponentScan("com.sunday.jewelry")
public class SecurityConfig {

    @Value("${jssesion.id}")
    private String jssesion_id;
    private final CustomUserDetailsService customUserDetailsService; // сервис, с помощью которого тащим пользователя
    private final SuccessUserHandler successUserHandler; // класс, в котором описана логика перенаправления пользователей по ролям

    public SecurityConfig(
            @Qualifier("customerDetailsServiceImpl") CustomUserDetailsService customUserDetailsService,
            SuccessUserHandler successUserHandler
    ) {
        this.customUserDetailsService = customUserDetailsService;
        this.successUserHandler = successUserHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf()
            .disable()
            .formLogin()
//                // указываем страницу с формой логина
            .loginPage("/login")
            // указываем action с формы логина
            .loginProcessingUrl("/login")
            // Указываем параметры логина и пароля с формы логина
            .usernameParameter("username")
            .passwordParameter("password")
            .permitAll()
            //указываем логику обработки при логине
            .successHandler(successUserHandler).and()
            .rememberMe().userDetailsService(customUserDetailsService)
            .key(jssesion_id)
            // даем доступ к форме логина всем
            .and()
            .rememberMe()
            .and()
            // делаем страницу регистрации недоступной для авторизированных пользователей
            .authorizeRequests()
            //страницы аутентификаци доступна всем
            .antMatchers("/favicon.ico").permitAll()
            .antMatchers("/login", "/registration", "/js/user/registration.js", "/css/registration.css").anonymous()
            //                 защищенные URL
            .antMatchers("/user/**").access("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
            .antMatchers("/**").access("hasRole('ROLE_ADMIN')").anyRequest().authenticated()
            .and()
            .logout()
            // разрешаем делать логаут всем
            .permitAll()
            // указываем URL логаута
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .deleteCookies("JSESSIONID", "remember-me")
            // указываем URL при удачном логауте
            .logoutSuccessUrl("/login")
            //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)
            .and().csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
