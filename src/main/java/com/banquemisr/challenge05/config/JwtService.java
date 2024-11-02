package com.banquemisr.challenge05.config;


import com.banquemisr.challenge05.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "uhHcvdzO3y0hUSSDd32uJic1Nm8fUItr2lTNSQctYxM";

    public JwtService() {}


    public static String generateToken(Map<String, Object> extraClaims,
                                                    User user) {
        String token = Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 24 *24))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

    public static String generateToken(User userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }



    private static Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        System.out.println("Decoded Key Bytes: " + Arrays.toString(keyBytes)); // Debugging line
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public <T> T extractClaims(String jwt, Function<Claims,T> claimsResolver) {
        final     Claims  claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String jwt) {
        return extractClaims(jwt, Claims::getSubject);
    }


    public boolean isValidJwt(String jwt , UserDetails user) {
       final String Username = extractUsername(jwt);
        return(Username.equals(user.getUsername()) && !isTokenExpired(jwt));
    }

    public boolean isTokenExpired(String token) {
       return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

}
