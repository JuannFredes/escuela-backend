package com.juan.escuela.services;

import com.juan.escuela.dto.AuthCredentialsDto;
import com.juan.escuela.dto.RegistroDto;
import com.juan.escuela.dto.UsuarioDto;
import com.juan.escuela.exceptions.AppException;
import com.juan.escuela.mappers.UsuarioMapper;
import com.juan.escuela.models.Usuario;
import com.juan.escuela.repositories.UsuarioRepository;
import com.juan.escuela.security.TokenUtils;
import com.juan.escuela.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenUtils tokenUtils;

    public UsuarioDto register(RegistroDto registroDto){
        if(usuarioRepository.existsUsuarioByUsername(registroDto.getUsername())) {
            throw new AppException("el usuario con el username \"" + registroDto.getUsername() + "\" ya existe",
                    HttpStatus.CONFLICT);
        }

        registroDto.setPassword(passwordEncoder.encode(registroDto.getPassword()));
        Usuario usuario = usuarioMapper.toUsuario(registroDto);
        Usuario usuarioSave = usuarioRepository.save(usuario);

        return getUsuarioDtoToken(usuarioSave);
    }

    public UsuarioDto login(AuthCredentialsDto authCredentialsDto) {
        Usuario usuario = usuarioRepository.getUser(authCredentialsDto.getUsername())
                .orElseThrow(() -> new AppException("el usuario no existe", HttpStatus.BAD_REQUEST));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authCredentialsDto.getUsername(),
                        authCredentialsDto.getPassword()
                )
        );

        return getUsuarioDtoToken(usuario);
    }

    private UsuarioDto getUsuarioDtoToken(Usuario usuario) {
        UserDetails userDetails = new UserDetailsImpl(usuario);
        String token = tokenUtils.createToken(userDetails.getUsername(), userDetails.getAuthorities());

        UsuarioDto usuarioDto = usuarioMapper.toUsuarioDto(usuario);
        usuarioDto.setToken(token);

        return usuarioDto;
    }
}
