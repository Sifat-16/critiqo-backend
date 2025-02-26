package com.critiqo.critiqo_backend.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {

    private static final String SECRET_KEY = "1705ebdf3523cf59a1bdba4428f5fe828ad2d9bfb207c47f2fcb44665ed3b0c0";

    public String generateToken(String subject){
        return Jwts.builder()
                .claims()
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() * 1000 * 60 * 50))
                .and()
                .signWith(getSignedKey())
                .compact();
    }

    public SecretKey getSignedKey(){
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractTokenExpirationTime(String token) {
        return extractClaims(token, Claims::getExpiration);
    }


    private <T> T extractClaims(String token, Function<Claims, T> claimExtraction){
        Claims claims = extractAllClaims(token);
        return claimExtraction.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignedKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Boolean isTokenExpired(String token) {
        return extractTokenExpirationTime(token).before(new Date());
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);

        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}