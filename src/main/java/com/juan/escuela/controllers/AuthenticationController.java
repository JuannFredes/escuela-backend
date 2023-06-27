package com.juan.escuela.controllers;

import com.juan.escuela.dto.AuthCredentialsDto;
import com.juan.escuela.dto.RegistroDto;
import com.juan.escuela.dto.UsuarioDto;
import com.juan.escuela.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<UsuarioDto> login(@RequestBody @Valid AuthCredentialsDto authCredentialsDto){
        UsuarioDto usuarioDto = authenticationService.login(authCredentialsDto);
        return ResponseEntity.ok(usuarioDto);
    }


    @PostMapping("/registrar")
    public ResponseEntity<UsuarioDto> registrar(@RequestBody @Valid RegistroDto registroDto) {
        UsuarioDto usuarioDto = authenticationService.resgitrar(registroDto);
        return ResponseEntity.ok(usuarioDto);
    }
}
