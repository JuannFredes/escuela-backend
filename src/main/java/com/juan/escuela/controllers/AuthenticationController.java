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


    @PostMapping("/register")
    public ResponseEntity<UsuarioDto> register(@RequestBody @Valid RegistroDto registroDto) {
        UsuarioDto usuarioDto = authenticationService.register(registroDto);
        return ResponseEntity.ok(usuarioDto);
    }
}
