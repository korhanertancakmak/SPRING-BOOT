package com.luv2code.springboot.thymeleafdemo.security;

import com.luv2code.springboot.thymeleafdemo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationSuccessHandler customAuthenticationSuccessHandler) throws Exception {

        http.authorizeHttpRequests(configurer -> configurer
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/signup").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/registration-confirmation").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/employees/list").authenticated()
                        .requestMatchers("/employees/AddNewEmployee", "/employees/updateEmployee", "/employees/save").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers("/users/**").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers("/delete").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers("/employees/delete").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .successHandler(customAuthenticationSuccessHandler)
                        .permitAll())
                .logout(logout -> logout.permitAll())
                .exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"));

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //authenticationProvider bean definition
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler(UserService userService) {
        return new CustomAuthenticationSuccessHandler(userService);  // Use your custom success handler
    }
}
