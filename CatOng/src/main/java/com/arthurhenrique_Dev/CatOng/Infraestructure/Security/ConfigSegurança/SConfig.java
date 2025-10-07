package com.arthurhenrique_Dev.CatOng.Infraestructure.Security.ConfigSegurança;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.Security;

@Configuration
@EnableWebSecurity
public class SConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST,"/pets").hasRole("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.PUT,"/pets").hasRole("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.DELETE, "/pets").hasRole("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.GET, "/user/gerenciamento").hasRole("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.POST, "/user/gerenciamento").hasRole("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.PUT, "/user/gerenciamento").hasRole("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.DELETE, "/user/gerenciamento").hasRole("GERENCIAMENTO")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
