package com.juan.escuela.mappers;

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

 /*   private static PodamFactory podamFactory;

    @BeforeAll
    static void setup() {
        podamFactory = new PodamFactoryImpl();
    }*/

    @Test
    void toUsuario() {
        UsuarioDto usuarioExpected = UsuarioDto.builder()
                .username("prueba")
                .password("154425")
                .roles(Set.of("USUARIO", "INVITADO"))
                .build();


        Usuario usuarioResult = usuarioMapper.toUsuario(usuarioExpected);
        Set<String> rolesResult = usuarioResult.getRoles().stream()
                .map( rol -> rol.getName().name())
                .collect(Collectors.toSet());

        assertAll( () -> {
            assertEquals(usuarioExpected.getUsername(), usuarioResult.getUsername());
            assertEquals(usuarioExpected.getPassword(), usuarioResult.getPassword());
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
            assertEquals(usuarioExpected.getPassword(), usuarioResult.getPassword());
            assertEquals(rolesExpected, usuarioResult.getRoles());
        });

    }
}
