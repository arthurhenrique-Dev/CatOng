package com.arthurhenrique_Dev.CatOng.Security.SecurityConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "user/gerenciamento/**").hasRole("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.PUT, "user/gerenciamento/**").hasRole("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.DELETE, "user/gerenciamento/**").hasRole("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.GET, "user/gerenciamento/**").hasRole("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.POST, "pets/gerenciamento/**").hasRole("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.PUT, "pets/gerenciamento/**").hasRole("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.DELETE, "pets/gerenciamento/**").hasRole("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.GET, "pets/gerenciamento/**").hasRole("GERENCIAMENTO")
                        .anyRequest().permitAll()
                )
                .build();
    };
}
