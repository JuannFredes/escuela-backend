/*package com.juan.escuela.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juan.escuela.dto.AuthCredentialsDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        AuthCredentialsDto authCredentialsDto = new AuthCredentialsDto();

        try {
            authCredentialsDto = new ObjectMapper().readValue(request.getReader(), AuthCredentialsDto.class);
        } catch (IOException e) {

        }


        UsernamePasswordAuthenticationToken userPat = new UsernamePasswordAuthenticationToken(
                authCredentialsDto.getUsername(),
                authCredentialsDto.getPassword(),
                Collections.emptyList()
        );

        return getAuthenticationManager().authenticate(userPat);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        UserDetailsImpl userDatails = (UserDetailsImpl) authResult.getPrincipal();
        String token = TokenUtils.createToken(userDatails.getUsername(), userDatails.getAuthorities());
        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}*/
