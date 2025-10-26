package com.arthurhenrique_Dev.CatOng.Security.Filter;

import com.arthurhenrique_Dev.CatOng.Security.SecurityService.AutenticacaoService;
import com.arthurhenrique_Dev.CatOng.Security.SecurityService.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final AutenticacaoService autenticacaoService;

    public SecurityFilter(TokenService tokenService, AutenticacaoService autenticacaoService) {
        this.tokenService = tokenService;
        this.autenticacaoService = autenticacaoService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null) {
            var subject = tokenService.ValidarToken(token);
            if (subject != null) {
                UserDetails user = autenticacaoService.loadUserByUsername(subject);
                var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var header = request.getHeader("Authorization");
        if (header == null) return null;
        return header.replace("Bearer ", "");
    }
}
