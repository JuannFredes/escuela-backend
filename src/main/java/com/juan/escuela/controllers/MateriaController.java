package com.juan.escuela.controllers;

import com.juan.escuela.dto.MateriaDetailsDto;
import com.juan.escuela.dto.MateriaDto;
import com.juan.escuela.models.Materia;
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

    @PutMapping
    public ResponseEntity<MateriaDto> putMateria(@RequestBody Materia materia){
        return ResponseEntity.ok(materiaService.putMateriaById(materia));
    }

    @PostMapping
    public ResponseEntity<MateriaDto> saveMateria(@Valid @RequestBody Materia materia) {
        return ResponseEntity.ok(materiaService.saveMateria(materia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateria(@PathVariable int id){
        materiaService.deleteMateriaById(id);
        return ResponseEntity.noContent().build();
    }

}
