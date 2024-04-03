package com.example.bookingplatform.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        return security
                .authorizeHttpRequests(
                        registry ->registry
                                .requestMatchers("/", "/auth/sign-in", "/auth/sign-up","/hotel/**", "/css/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .formLogin(
                        loginConfig -> loginConfig
                                .loginPage("/auth/sign-in")
                                .defaultSuccessUrl("/")
                                .loginProcessingUrl("/auth/sign-in")
                                .usernameParameter("username")
                                .passwordParameter("password")
                )
                .logout(
                        logoutConfig -> logoutConfig
                                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/sign-out"))
                                .logoutSuccessUrl("/auth/sign-in")
                )
                .rememberMe(
                        rememberMeConfig -> {
                            rememberMeConfig
                                    .rememberMeCookieName("rememberMe")
                                    .tokenValiditySeconds(3 * 60 * 60 * 60 * 24)
                                    .rememberMeParameter("rememberMe");
                        }
                ).build();
    }
}
