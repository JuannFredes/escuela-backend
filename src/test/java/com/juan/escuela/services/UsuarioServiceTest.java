package com.juan.escuela.services;

import com.juan.escuela.dto.UsuarioDto;
import com.juan.escuela.mappers.UsuarioMapper;
import com.juan.escuela.mappers.UsuarioMapperImpl;
import com.juan.escuela.models.ERol;
import com.juan.escuela.models.Rol;
import com.juan.escuela.models.Usuario;
import com.juan.escuela.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuariosService usuariosService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Spy
    private UsuarioMapper usuarioMapper = new UsuarioMapperImpl();

    @Test
    void getAllUserTest() {
        List<Usuario> usuariosExpected = List.of(
                Usuario.builder()
                        .id(1)
                        .username("Prueba1")
                        .password("PassW1")
                        .roles(Set.of(
                                Rol.builder().id(1).name(ERol.ADMIN).build()
                        ))
                        .build(),
                Usuario.builder()
                        .id(2)
                        .username("Prueba2")
                        .password("PassW2")
                        .roles(Set.of(
                                Rol.builder().id(1).name(ERol.ADMIN).build(),
                                Rol.builder().id(2).name(ERol.USUARIO).build()
                        ))
                        .build()
        );
        List<Set<String>> rolesExpected = usuariosExpected.stream()
                .map( listUser -> {
                    Set<String> rolesString = listUser.getRoles().stream().map( rol -> rol.getName().name() )
                            .collect(Collectors.toSet());
                    return rolesString;
                }).collect(Collectors.toList());

        when(usuarioRepository.findAll()).thenReturn(usuariosExpected);

        List<UsuarioDto> usuarioDtosResult = usuariosService.getAllUser();
        String tokenExpected = "*****";

        assertAll(() -> {
            assertEquals(usuariosExpected.get(0).getUsername(), usuarioDtosResult.get(0).getUsername());
            assertEquals(rolesExpected.get(0), usuarioDtosResult.get(0).getRoles());
            assertEquals(tokenExpected, usuarioDtosResult.get(0).getToken());
            assertEquals(usuariosExpected.get(1).getUsername(), usuarioDtosResult.get(1).getUsername());
            assertEquals(rolesExpected.get(1), usuarioDtosResult.get(1).getRoles());
            assertEquals(tokenExpected, usuarioDtosResult.get(1).getToken());
        });
        verify(usuarioRepository).findAll();
        verify(usuarioMapper).toListUsuarioDto(usuariosExpected);
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
