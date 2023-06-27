package com.juan.escuela.controllers;

import com.juan.escuela.dto.UsuarioDto;
import com.juan.escuela.services.UsuariosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    private final UsuariosService usuariosService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> getAllUser(){
        List<UsuarioDto> usuarioDtos = usuariosService.getAllUser();
        return ResponseEntity.ok(usuarioDtos);
    }

    /*@PostMapping
    public ResponseEntity<UsuarioDto> saveUsuario(@RequestBody UsuarioDto usuarioDto) {
        usuarioDto.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));//encripto la contraseña apenas se recibe
        UsuarioDto usuarioDtoSave = usuariosService.createUser(usuarioDto);

        return ResponseEntity.ok(usuarioDtoSave);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable int id){
        usuariosService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUsuario(@RequestParam(value = "username") String user){
        usuariosService.deleteUsuario(user);
        return ResponseEntity.noContent().build();
    }
}
