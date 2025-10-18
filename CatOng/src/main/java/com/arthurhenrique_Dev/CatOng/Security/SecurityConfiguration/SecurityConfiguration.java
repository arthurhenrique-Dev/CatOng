package com.arthurhenrique_Dev.CatOng.Security.SecurityConfiguration;

import com.arthurhenrique_Dev.CatOng.Security.Filter.SecurityFilter;
import com.arthurhenrique_Dev.CatOng.Security.SecurityService.AutenticacaoService;
import com.arthurhenrique_Dev.CatOng.Security.SecurityService.TokenService;
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

    private final TokenService tokenService;
    private final AutenticacaoService autenticacaoService;

    public SecurityConfiguration(TokenService tokenService, AutenticacaoService autenticacaoService) {
        this.tokenService = tokenService;
        this.autenticacaoService = autenticacaoService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        SecurityFilter securityFilter = new SecurityFilter(tokenService, autenticacaoService);

        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/user/gerenciamento/**").hasAuthority("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.PUT, "/user/gerenciamento/**").hasAuthority("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.DELETE, "/user/gerenciamento/**").hasAuthority("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.GET, "/user/gerenciamento/**").hasAuthority("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.POST, "/pets/gerenciamento/**").hasAuthority("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.PUT, "/pets/gerenciamento/**").hasAuthority("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.DELETE, "/pets/gerenciamento/**").hasAuthority("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.GET, "/pets/gerenciamento/**").hasAuthority("GERENCIAMENTO")
                        .requestMatchers(HttpMethod.POST, "/user/gerenciamento/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/user/gerenciamento/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user/gerenciamento/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/user/gerenciamento/**").hasAuthority("ADMIN")
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
