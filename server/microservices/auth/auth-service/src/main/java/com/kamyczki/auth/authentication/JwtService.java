package com.kamyczki.auth.authentication;

import com.kamyczki.commons.error.ErrorException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
class JwtService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private Key key;

    String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Try.of(()->extractAllClaims(token))
                .onFailure(ExpiredJwtException.class,thr -> {
                    throw new ErrorException("TOKEN_EXPIRED","Jwt token expired", HttpStatus.UNAUTHORIZED);
                })
                .get();
        return claimsResolver.apply(claims);
    }

    String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))  // 24 hours
                .signWith(getSignInKey())
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        if (this.key == null) {
            byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
            this.key = Keys.hmacShaKeyFor(keyBytes);
        }
        return this.key;
    }
}