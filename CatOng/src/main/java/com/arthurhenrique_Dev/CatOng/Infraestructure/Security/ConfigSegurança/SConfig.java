package com.arthurhenrique_Dev.CatOng.Infraestructure.Security.ConfigSegurança;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST,"/pets").hasRole("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.PUT,"/pets").hasRole("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.DELETE, "/pets").hasRole("GERENCIAMENTO")
                        .anyRequest().authenticated())
                .build();
    }
}
