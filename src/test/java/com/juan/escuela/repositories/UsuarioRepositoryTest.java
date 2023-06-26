package com.juan.escuela.repositories;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.juan.escuela.models.ERol;
import com.juan.escuela.models.Rol;
import com.juan.escuela.models.Usuario;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @DatabaseSetup("/dataset/rols.xml")
    @DatabaseSetup("/dataset/usuarios.xml")
    @DatabaseSetup("/dataset/USUARIOS_ROLES.xml")
    void getUserTest(){
        Usuario usuario = usuarioRepository.getUser("director").get();
        Set<Rol> rolesExpected = Set.of(
                Rol.builder()
                        .id(1)
                        .name(ERol.ADMIN).build(),
                Rol.builder()
                        .id(2)
                        .name(ERol.USUARIO).build());

        assertAll(() -> {
            assertEquals(1, usuario.getId());
            assertEquals("director", usuario.getUsername());
            assertEquals("password1", usuario.getPassword());
            assertEquals(rolesExpected, usuario.getRoles());
        });
    }

    @Test
    @DatabaseSetup("/dataset/rols.xml")
    @DatabaseSetup("/dataset/usuarios.xml")
    @DatabaseSetup("/dataset/USUARIOS_ROLES.xml")
    void existsUsuarioByUsernameTest() {
        boolean expected = true;
        boolean result = usuarioRepository.existsUsuarioByUsername("director");

        assertEquals(expected, result);
    }

    @Test
    @DatabaseSetup("/dataset/rols.xml")
    @DatabaseSetup("/dataset/usuarios.xml")
    @DatabaseSetup("/dataset/USUARIOS_ROLES.xml")
    void deleteUsuarioByUsernameTest(){
        short expected = 1;
        short result = usuarioRepository.deleteUsuarioByUsername("director");

        assertEquals(expected, result);
    }

    @Test
    @DatabaseSetup("/dataset/rols.xml")
    @DatabaseSetup("/dataset/usuarios.xml")
    @DatabaseSetup("/dataset/USUARIOS_ROLES.xml")
    void deleteUsuarioByIdTest() {
        boolean expectedBefore = true;
        boolean resultBefore = usuarioRepository.existsById(1);
        usuarioRepository.deleteById(1);
        boolean expectedAfter = false;
        boolean resultAfter = usuarioRepository.existsById(1);

        assertEquals(expectedBefore, resultBefore);
        assertEquals(expectedAfter, resultAfter);
    }
}
