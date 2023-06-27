package com.juan.escuela.security;

import com.juan.escuela.models.ERol;
import com.juan.escuela.repositories.UsuarioRepository;
import com.juan.escuela.services.UserDetailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.juan.escuela.models.ERol.*;

@AllArgsConstructor
@Configuration
//@EnableWebSecurity(debug = true)
public class WebSecurityConfig {

    private final UserDetailsService userDatailsService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    //<private final JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
       /*JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
       jwtAuthenticationFilter.setAuthenticationManager(authManager);
       jwtAuthenticationFilter.setFilterProcessesUrl("/login");*/

        return http
                .exceptionHandling( customer -> customer.authenticationEntryPoint(authenticationEntryPoint))
                .csrf().disable()
                .authorizeHttpRequests( auth -> {
                    auth.antMatchers("/v1/auth/login").permitAll();
                    auth.antMatchers("/v1/auth/registrar").hasRole(ADMIN.name());
                    auth.antMatchers("/v1/usuarios/**").hasRole(ADMIN.name());
                    auth.antMatchers(HttpMethod.GET).hasAnyRole(ADMIN.name(), INVITADO.name(), USUARIO.name());
                    auth.antMatchers(HttpMethod.POST).hasAnyRole(ADMIN.name(), USUARIO.name());
                    auth.antMatchers(HttpMethod.PUT).hasAnyRole(ADMIN.name(), USUARIO.name());
                    auth.antMatchers(HttpMethod.DELETE).hasAnyRole(ADMIN.name(), USUARIO.name());
                    auth.anyRequest().authenticated();
                })
                .sessionManagement( session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authenticationProvider(authenticationProvider())
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
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDatailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
