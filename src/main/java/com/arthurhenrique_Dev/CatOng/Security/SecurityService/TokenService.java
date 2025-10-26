package com.arthurhenrique_Dev.CatOng.Security.SecurityService;

import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Permissao;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class TokenService {
    ;

    public String GerarTokenGenerico(String login, Permissao permissao) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("chave");
            return JWT.create()
                    .withIssuer("CatOng")
                    .withSubject(login)
                    .withClaim("permissao", permissao.name())
                    .withExpiresAt(Date.from(Instant.now().plusSeconds(86400)))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar JWT", exception);
        }
    }

    public String ValidarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("chave");
            return JWT.require(algorithm)
                    .withIssuer("CatOng")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
}
