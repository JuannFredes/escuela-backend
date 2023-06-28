package com.juan.escuela.services;

import com.juan.escuela.dto.UsuarioDto;
import com.juan.escuela.exceptions.AppException;
import com.juan.escuela.mappers.UsuarioMapper;
import com.juan.escuela.models.Usuario;
import com.juan.escuela.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuariosService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public List<UsuarioDto> getAllUser() {
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
        List<UsuarioDto> usuarioDtos = usuarioMapper.toListUsuarioDto(usuarios);

        return usuarioDtos;
    }

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
