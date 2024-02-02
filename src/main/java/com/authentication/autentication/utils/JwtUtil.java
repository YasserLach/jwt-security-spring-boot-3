package com.authentication.autentication.utils;
import com.authentication.autentication.exception.JwtTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Value("${spring.access-secret}")
    private String accessToken;

    @Value("${spring.access-token.expiration}")
    private Long accessTokenExp;

    @Value("${spring.refresh-token.expiration}")
    private Long refreshTokenExp;


    public String generateToken(Map<String,Object> extraClaims,String username) {
        extraClaims.put("tokenType","ACCESS");
        return buildToken(extraClaims,username,accessTokenExp);
    }

    public String generateRefreshToken(Map<String,Object> extraClaims, String username) {
        extraClaims.put("tokenType","REFRESH");
        return buildToken(extraClaims,username,refreshTokenExp);
    }

    private String buildToken(Map<String, Object> extractClaims,
                              String username,
                              Long jwtExpiration
    ) {
        return Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(username)
                .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(jwtExpiration).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }



    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

        public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
            final Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);
        }

    private Claims extractAllClaims(String token) {
            return Jwts
                    .parser()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(accessToken);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
            final String username = extractUsername(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

}
