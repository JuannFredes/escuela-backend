package com.juan.escuela.security;

import com.juan.escuela.repositories.UsuarioRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class TokenUtils {

    @Value("${application.security.jwt.secret-key}")
    private String ACCESS_TOKEN_SECRET;

    @Value("${application.security.jwt.expiration}")
    private String ACCESS_TOKEN_VALIDITY_SECONDS;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String createToken(String user, Collection<? extends GrantedAuthority> roles) {
        long expirationTime = Long.parseLong(ACCESS_TOKEN_VALIDITY_SECONDS) * 1000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        List<String> rolesList = roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        Map<String, Object> extra = new HashMap<>();
        extra.put("Roles", rolesList);

        return Jwts.builder()
                .setSubject(user)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token){
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String user = claims.getSubject();
            List<String> listRoles = (List<String>) claims.get("Roles");

            Collection<? extends GrantedAuthority> roles = listRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
            return new UsernamePasswordAuthenticationToken(user, null, roles);
        }catch (JwtException e) {
            return null;
        }
    }
}
