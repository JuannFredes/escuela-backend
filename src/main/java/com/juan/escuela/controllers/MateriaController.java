package com.juan.escuela.controllers;

import com.juan.escuela.dto.MateriaDetailsDto;
import com.juan.escuela.dto.MateriaDto;
import com.juan.escuela.services.MateriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v2/materias")
@Tag(name = "Materias")
public class MateriaController {

    private final MateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<MateriaDto>> getAllMateria(){
        return ResponseEntity.ok(materiaService.getAllMateria());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDetailsDto> getMateria(@PathVariable int id) {
        return ResponseEntity.ok(materiaService.getMateria(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaDto> updateMateria(@PathVariable int id, @RequestBody MateriaDto materiaDto){
        return ResponseEntity.ok(materiaService.updateMateria(id, materiaDto));
    }

    @PostMapping
    public ResponseEntity<MateriaDto> saveMateria(@Valid @RequestBody MateriaDto materiaDto) {
        return ResponseEntity.ok(materiaService.saveMateria(materiaDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateria(@PathVariable int id){
        materiaService.deleteMateria(id);
        return ResponseEntity.noContent().build();
    }

}
