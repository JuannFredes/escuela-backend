package com.juan.escuela.services;

import com.juan.escuela.dto.UsuarioDto;
import com.juan.escuela.mappers.UsuarioMapper;
import com.juan.escuela.mappers.UsuarioMapperImpl;
import com.juan.escuela.models.ERol;
import com.juan.escuela.models.Rol;
import com.juan.escuela.models.Usuario;
import com.juan.escuela.repositories.RolRepository;
import com.juan.escuela.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuariosService usuariosService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RolRepository rolRepository;

    @Spy
    private UsuarioMapper usuarioMapper = new UsuarioMapperImpl();

    @Test
    void createUser() {
        UsuarioDto usuarioDtoExpected = UsuarioDto.builder()
                .username("prueba")
                .password("12345678")
                .roles(Set.of("ADMIN", "USUARIO"))
                .build();
        Usuario usuario = usuarioMapper.toUsuario(usuarioDtoExpected);

        usuarioDtoExpected.getRoles().forEach(rol -> {
            when(rolRepository.getRol(rol)).thenReturn(Optional.of(Rol.builder()
                    .name(ERol.valueOf(rol))
                    .build()));
        });
        when(usuarioRepository.existsUsuarioByUsername(usuarioDtoExpected.getUsername())).thenReturn(false);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        UsuarioDto usuarioDtoResult = usuariosService.createUser(usuarioDtoExpected);

        assertAll( () -> {
            assertEquals(usuarioDtoExpected.getUsername(), usuarioDtoResult.getUsername());
            assertEquals(usuarioDtoExpected.getPassword(), usuarioDtoResult.getPassword());
            assertEquals(usuarioDtoExpected.getRoles(), usuarioDtoResult.getRoles());
        });
    }

    @Test
    void deleteUsuarioByIdTest(){
        when(usuarioRepository.existsById(1)).thenReturn(true);
        usuariosService.deleteUsuario(1);

        verify(usuarioRepository).existsById(1);
        verify(usuarioRepository).deleteById(1);
    }

    @Test
    void deleteUsuarioByUsernameTest(){
        String username = "preuba";

        when(usuarioRepository.existsUsuarioByUsername(username)).thenReturn(true);
        usuariosService.deleteUsuario(username);

        verify(usuarioRepository).existsUsuarioByUsername(username);
        verify(usuarioRepository).deleteUsuarioByUsername(username);
    }

}
