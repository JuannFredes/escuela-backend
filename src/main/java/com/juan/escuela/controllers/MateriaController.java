package com.juan.escuela.controllers;

import com.juan.escuela.dto.MateriaDetailsDto;
import com.juan.escuela.dto.MateriaDto;
import com.juan.escuela.services.MateriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/materias")
public class MateriaController {

    private final MateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<MateriaDto>> getAll(){
        return ResponseEntity.ok(materiaService.getAllMaterias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDetailsDto> getMateria(@PathVariable int id) {
        return ResponseEntity.ok(materiaService.getMateriaById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaDto> updateMateria(@PathVariable int id, @RequestBody MateriaDto materiaDto){
        return ResponseEntity.ok(materiaService.putMateria(id, materiaDto));
    }

    @PostMapping
    public ResponseEntity<MateriaDto> saveMateria(@Valid @RequestBody MateriaDto materiaDto) {
        return ResponseEntity.ok(materiaService.saveMateria(materiaDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateria(@PathVariable int id){
        materiaService.deleteMateriaById(id);
        return ResponseEntity.noContent().build();
    }

}
