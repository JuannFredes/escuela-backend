package com.juan.escuela.controllers;

import com.juan.escuela.dto.ProfesorDto;
import com.juan.escuela.services.ProfesorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v2/profesores")
@Tag(name = "Profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    @GetMapping
    public ResponseEntity<List<ProfesorDto>> getAllProfesor(){
        return ResponseEntity.ok(profesorService.getAllProfesor());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorDto> getProfesor(@PathVariable int id){
        return ResponseEntity.ok(profesorService.getProfesor(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesorDto> updateProfesor(@PathVariable int id, @RequestBody ProfesorDto profesorDto){
        return ResponseEntity.ok(profesorService.updateProfesor(id, profesorDto));
    }

    @PostMapping
    public ResponseEntity<ProfesorDto> saveProfesor(@Valid @RequestBody ProfesorDto profesorDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(profesorService.saveProfesor(profesorDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable int id) {
        profesorService.deleteProfesor(id);
        return ResponseEntity.noContent().build();
    }
}
