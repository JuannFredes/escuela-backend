package com.juan.escuela.mappers;

import com.juan.escuela.dto.RegistroDto;
import com.juan.escuela.dto.UsuarioDto;
import com.juan.escuela.models.ERol;
import com.juan.escuela.models.Rol;
import com.juan.escuela.models.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioMapperTest {

    private UsuarioMapper usuarioMapper = new UsuarioMapperImpl();


    @Test
    void toUsuarioFromUsuarioDtoTest() {
        UsuarioDto usuarioExpected = UsuarioDto.builder()
                .username("prueba")
                .roles(Set.of("USUARIO", "INVITADO"))
                .build();


        Usuario usuarioResult = usuarioMapper.toUsuario(usuarioExpected);
        Set<String> rolesResult = usuarioResult.getRoles().stream()
                .map( rol -> rol.getName().name())
                .collect(Collectors.toSet());

        assertAll( () -> {
            assertEquals(usuarioExpected.getUsername(), usuarioResult.getUsername());
            assertEquals(usuarioExpected.getRoles(), rolesResult);
        });
    }

    @Test
    void toUsuarioDtoTest(){
        Usuario usuarioExpected = Usuario.builder()
                .id(1)
                .username("prueba")
                .password("134212")
                .roles(Set.of(
                        Rol.builder().id(1).name(ERol.ADMIN).build(),
                        Rol.builder().id(2).name(ERol.USUARIO).build()
                ))
                .build();
        Set<String> rolesExpected = usuarioExpected.getRoles().stream()
                .map( rol -> rol.getName().name())
                .collect(Collectors.toSet());


        UsuarioDto usuarioResult = usuarioMapper.toUsuarioDto(usuarioExpected);

        assertAll( () -> {
            assertEquals(usuarioExpected.getUsername(), usuarioResult.getUsername());
            assertEquals(rolesExpected, usuarioResult.getRoles());
        });

    }

    @Test
    void toListUsuarioDto() {
        List<Usuario> usuariosExpected = List.of(
                Usuario.builder()
                        .id(1)
                        .username("Prueba1")
                        .password("Pass1")
                        .roles(Set.of(Rol.builder().id(1).name(ERol.ADMIN).build()))
                        .build(),
                Usuario.builder()
                        .id(2)
                        .username("Prueba2")
                        .password("Pass2")
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

        List<UsuarioDto> usuarioDtosResult = usuarioMapper.toListUsuarioDto(usuariosExpected);
        String tokenExpected = "*****";

        assertAll(() -> {
            assertEquals(usuariosExpected.get(0).getUsername(), usuarioDtosResult.get(0).getUsername());
            assertEquals(rolesExpected.get(0), usuarioDtosResult.get(0).getRoles());
            assertEquals(tokenExpected, usuarioDtosResult.get(0).getToken());
            assertEquals(usuariosExpected.get(1).getUsername(), usuarioDtosResult.get(1).getUsername());
            assertEquals(rolesExpected.get(1), usuarioDtosResult.get(1).getRoles());
            assertEquals(tokenExpected, usuarioDtosResult.get(1).getToken());
        });
    }

    @Test
    void toUsuarioFromRegistroDtoTest() {
        RegistroDto registroDtoExpected = RegistroDto.builder()
                .username("Prueba")
                .password("Pass")
                .build();


        Usuario usuarioResult = usuarioMapper.toUsuario(registroDtoExpected);
        Set<String> rolesStringResult = usuarioResult.getRoles().stream()
                .map( rol -> rol.getName().name())
                        .collect(Collectors.toSet());

        assertAll(() -> {
            assertEquals(registroDtoExpected.getUsername(), usuarioResult.getUsername());
            assertEquals(registroDtoExpected.getPassword(), usuarioResult.getPassword());
        });
    }
}
