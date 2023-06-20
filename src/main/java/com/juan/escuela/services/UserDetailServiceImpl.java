package com.juan.escuela.security;

import com.juan.escuela.exceptions.AppException;
import com.juan.escuela.models.Usuario;
import com.juan.escuela.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.getUser(user)
                .orElseThrow(() -> new AppException("el usuario no existe", HttpStatus.NOT_FOUND));

        return new UserDatailsImpl(usuario);
    }
}
