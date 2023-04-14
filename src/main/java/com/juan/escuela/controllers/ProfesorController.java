package com.juan.escuela.controllers;

import com.juan.escuela.dto.ProfesorDto;
import com.juan.escuela.services.ProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    @GetMapping
    public ResponseEntity<List<ProfesorDto>> getAllProfesor(){
        return ResponseEntity.ok(profesorService.getAllProfesores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorDto> getProfesor(@PathVariable int id){
        return ResponseEntity.ok(profesorService.getProfesorById(id));
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
        profesorService.deleteProfesorByid(id);
        return ResponseEntity.noContent().build();
    }
}
