package com.juan.escuela.security;

import com.juan.escuela.models.ERol;
import com.juan.escuela.models.Rol;
import com.juan.escuela.models.Usuario;
import com.juan.escuela.repositories.UsuarioRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class TokenUtils {

    private final static String ACCESS_TOKEN_SECRET = "aiubiubs$%%N45inbubiubsbiu7/(/$&%TYUYYU(YVY/V/uybub75465dvuyhb";
    private final static long ACCESS_TOKEN_VALIDITY_SECONDS = 2_000_000L;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public static String createToken(String user, Collection<? extends GrantedAuthority> roles) {
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
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

    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
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
