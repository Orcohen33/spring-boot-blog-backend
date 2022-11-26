package com.orcohen.blogrestapi.security;


import com.orcohen.blogrestapi.utils.RsaKeyProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Instant;
import java.util.stream.Collectors;

@Component
@Slf4j
/**
 * This class is responsible for creating a JWT token
 * and validating a JWT token
 */
public class JwtTokenUtils {

    private final JwtEncoder encoder;


    private final RsaKeyProperties rsaKeyProperties;
    public JwtTokenUtils(JwtEncoder jwtEncoder, RsaKeyProperties rsaKeyProperties) {
        this.encoder = jwtEncoder;
        this.rsaKeyProperties = rsaKeyProperties;
    }

    public String generateToken(Authentication auth){
        Instant now = Instant.now();
        String scope = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("http://localhost:8081")
                .issuedAt(now)
                .expiresAt(now.plus(1, java.time.temporal.ChronoUnit.HOURS))
                .subject(auth.getName())
                .claim("scope", scope)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public Boolean validateToken(String token) throws ParseException {
        String username = getUserNameFromToken(token);
        Claims claims = Jwts.parser()
                .setSigningKey(rsaKeyProperties.publicKey())
                .parseClaimsJws(token)
                .getBody();
        boolean isTokenExpired = claims.getExpiration().before(new java.util.Date());
        return (username.equals(claims.getSubject()) && !isTokenExpired);
    }
    public String getUserNameFromToken(String token){
        // Get the subject from the token
        final Claims claims = Jwts.parser().setSigningKey(rsaKeyProperties.publicKey()).parseClaimsJws(token).getBody();
        log.info("Claims: {}", claims);
        return claims.getSubject();
    }
}
