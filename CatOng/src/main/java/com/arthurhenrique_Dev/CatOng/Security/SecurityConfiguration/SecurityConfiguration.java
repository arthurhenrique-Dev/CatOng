package com.arthurhenrique_Dev.CatOng.Security.SecurityConfiguration;

import com.arthurhenrique_Dev.CatOng.Security.Filter.SecurityFilter;
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

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, SecurityFilterChain securityFilterChain) throws Exception {
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
                        .requestMatchers(HttpMethod.POST, "user/gerenciamento/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "user/gerenciamento/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "user/gerenciamento/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "user/gerenciamento/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
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
