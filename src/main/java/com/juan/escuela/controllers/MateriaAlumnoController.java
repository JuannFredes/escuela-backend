package com.juan.escuela.controllers;

import com.juan.escuela.dto.MateriaNotaDto;
import com.juan.escuela.services.MateriaAlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/materianota")
public class MateriaAlumnoController {

    private final MateriaAlumnoService materiaAlumnoService;

    @PostMapping("/{idAlumno}")
    public ResponseEntity<MateriaNotaDto> updateMateriaNota(@PathVariable("idAlumno") int id, @Valid @RequestBody MateriaNotaDto materiaNotaDto){
        return ResponseEntity.ok(materiaAlumnoService.createPutMateriaNota(id, materiaNotaDto));
    }
}
