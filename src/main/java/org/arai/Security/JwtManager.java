package org.arai.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import jakarta.annotation.PostConstruct;
import org.arai.Dto.jwt.JwtAudit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.*;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtManager {

    @Value("${jwt.secret}")
    private String secret;
    private  SecretKey secretKey;
    private static final long EXPIRATION_TIME = 86400000; // 24 horas en milisegundos

    @PostConstruct
    public void init(){
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }


    public String generateToken(String userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", userId);

        Instant now = Instant.now();
        Instant expiration = now.plusMillis(EXPIRATION_TIME);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiration))
                .signWith(secretKey)
                .compact();
    }

    public JwtAudit parseAndValidateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // Verificar expiración
            if (claims.getExpiration().before(new Date())) {
                throw new ExpiredJwtException(null, claims, "Token expirado");
            }

            return new JwtAudit(
                    claims.get("user_id", String.class),
                    claims.getIssuedAt().toInstant(),
                    claims.getExpiration().toInstant()
            );
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token expirado", e);
        } catch (Exception e) {
            throw new RuntimeException("Token inválido", e);
        }
    }



}
