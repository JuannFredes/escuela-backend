package com.juan.escuela.services;

import com.juan.escuela.models.Usuario;
import com.juan.escuela.repositories.UsuarioRepository;
import com.juan.escuela.security.UserDetailsImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class UserDetailServiceImplTest {

    @InjectMocks
    private UserDetailServiceImpl userDetailService;

    @Mock
    private UsuarioRepository usuarioRepository;

    private static PodamFactory podamFactory;

    @BeforeAll
    static void setup(){
        podamFactory = new PodamFactoryImpl();
    }

    @Test
    void loadUserByUsernameTest() {
        Usuario usuarioExpected = podamFactory.manufacturePojo(Usuario.class);
        Collection<? extends GrantedAuthority> collectExpected = usuarioExpected.getRoles().stream()
                        .map( rol -> new SimpleGrantedAuthority("ROLE_".concat(rol.getName().name())))
                        .collect(Collectors.toSet());

        when(usuarioRepository.getUser("director")).thenReturn(Optional.of(usuarioExpected));

        UserDetailsImpl userDetailsResult = (UserDetailsImpl) userDetailService.loadUserByUsername("director");
        verify(usuarioRepository).getUser("director");

        assertAll(() -> {
            assertEquals(usuarioExpected.getUsername(), userDetailsResult.getUsername());
            assertEquals(usuarioExpected.getPassword(), userDetailsResult.getPassword());
            assertEquals(usuarioExpected.getRoles(), userDetailsResult.getUsuario().getRoles());
        });
    }
}
