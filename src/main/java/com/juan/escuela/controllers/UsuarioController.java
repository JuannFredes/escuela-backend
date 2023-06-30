package com.juan.escuela.controllers;

import com.juan.escuela.dto.UsuarioDto;
import com.juan.escuela.services.UsuariosService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v2/usuarios")
@Tag(name = "Usuarios")
public class UsuarioController {

    private final UsuariosService usuariosService;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> getAllUsuario(){
        List<UsuarioDto> usuarioDtos = usuariosService.getAllUser();
        return ResponseEntity.ok(usuarioDtos);
    }

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
