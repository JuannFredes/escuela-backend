package com.juan.escuela.controllers;

import com.juan.escuela.dto.MateriaNotaDto;
import com.juan.escuela.services.MateriaAlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/materianota")
public class MateriaAlumnoController {
    @Autowired
    private MateriaAlumnoService materiaAlumnoService;

    @PostMapping("/{idAlumno}")
    public ResponseEntity<MateriaNotaDto> updateMateriaNota(@PathVariable("idAlumno") int id, @RequestBody MateriaNotaDto materiaNotaDto){
        return ResponseEntity.ok(materiaAlumnoService.createPutMateriaNota(id, materiaNotaDto));
    }
}
