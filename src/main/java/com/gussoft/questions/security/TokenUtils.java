package com.gussoft.questions.security;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class TokenUtils {

    //remember a100;
    private final static String ACCESS_TOKEN = "WKpqMJZfZ7olBRngXiD7E944K7U9Y6sdY6m8dCQUF80";

    private final static Long AT_VALIDITY_SECONDS = 2_592_000L;

    public static String createToken(String name, String email) {
        long expirationTime = AT_VALIDITY_SECONDS * 1_000L;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", name);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .setClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String email = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (JwtException e) {
            return null;
        }
    }
}
