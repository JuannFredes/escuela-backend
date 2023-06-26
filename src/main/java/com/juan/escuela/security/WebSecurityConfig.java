package com.juan.escuela.security;

import com.juan.escuela.models.ERol;
import com.juan.escuela.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
@Configuration
//@EnableWebSecurity(debug = true)
public class WebSecurityConfig {

    private final UserDetailsService userDatailsService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    SecurityFilterChain filterChain (HttpSecurity http, AuthenticationManager authManager) throws Exception {
       JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
       jwtAuthenticationFilter.setAuthenticationManager(authManager);
       jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return http
                .csrf().disable()
                .authorizeHttpRequests( auth -> {
                    auth.antMatchers("/v1/usuarios/**").hasRole(ERol.ADMIN.name());
                    auth.antMatchers("/v1/usuarios/**").hasRole(ERol.USUARIO.name());
                    auth.antMatchers(HttpMethod.GET).hasAnyRole(ERol.ADMIN.name(), ERol.INVITADO.name(), ERol.USUARIO.name());
                    auth.antMatchers(HttpMethod.POST).hasAnyRole(ERol.ADMIN.name(), ERol.USUARIO.name());
                    auth.antMatchers(HttpMethod.PUT).hasAnyRole(ERol.ADMIN.name(), ERol.USUARIO.name());
                    auth.antMatchers(HttpMethod.DELETE).hasAnyRole(ERol.ADMIN.name(), ERol.USUARIO.name());
                    auth.anyRequest().authenticated();
                })
                .sessionManagement( session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

/*    @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("12345678"))
                .roles()
                .build());
        return manager;
    }*/

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDatailsService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
