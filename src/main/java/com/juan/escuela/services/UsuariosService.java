package com.juan.escuela.services;

import com.juan.escuela.dto.UsuarioDto;
import com.juan.escuela.exceptions.AppException;
import com.juan.escuela.mappers.UsuarioMapper;
import com.juan.escuela.models.Rol;
import com.juan.escuela.models.Usuario;
import com.juan.escuela.repositories.RolRepository;
import com.juan.escuela.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UsuariosService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final RolRepository rolRepository;

    public List<UsuarioDto> getAllUser() {
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
        List<UsuarioDto> usuarioDtos = usuarioMapper.toListUsuarioDto(usuarios);
       // usuarioDtos.forEach(user -> user.setToken("*****"));

        return usuarioDtos;
    }
    /*public UsuarioDto createUser(UsuarioDto usuarioDto) {
        Set<Rol> roles = usuarioDto.getRoles().stream()
                .map(rol -> rolRepository.getRol(rol)
                        .orElseThrow(() -> new AppException("el rol indicado no existe", HttpStatus.BAD_REQUEST)))
                .collect(Collectors.toSet());

        Usuario usuario = usuarioMapper.toUsuario(usuarioDto);
        usuario.setRoles(roles);

        if (usuarioRepository.existsUsuarioByUsername(usuario.getUsername())){
            throw new AppException("el usuario elegido ya existe, elija otro nombre de usuario", HttpStatus.BAD_REQUEST);
        }

        Usuario usuarioSave = usuarioRepository.save(usuario);

        return usuarioMapper.toUsuarioDto(usuarioSave);
    }*/

    @Transactional
    public void deleteUsuario(int id) {
        if(!usuarioRepository.existsById(id)) {
            throw new AppException("el usuario con el id " + id + " no existe", HttpStatus.BAD_REQUEST);
        }

        usuarioRepository.deleteById(id);
    }

    @Transactional
    public void deleteUsuario(String user) {
        if(!usuarioRepository.existsUsuarioByUsername(user)){
            throw new AppException("el usuario con el username " + user + " no existe", HttpStatus.BAD_REQUEST);
        }

        usuarioRepository.deleteUsuarioByUsername(user);
    }
}
