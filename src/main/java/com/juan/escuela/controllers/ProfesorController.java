package com.juan.escuela.controllers;

import com.juan.escuela.dto.ProfesorDto;
import com.juan.escuela.models.Profesor;
import com.juan.escuela.services.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping
    public ResponseEntity<List<ProfesorDto>> getAllProfesor(){
        return ResponseEntity.ok(profesorService.getAllProfesores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorDto> getProfesor(@PathVariable int id){
        return ResponseEntity.ok(profesorService.getProfesorById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesorDto> updateProfesor(@PathVariable int id, @RequestBody Profesor profesor){
        return ResponseEntity.ok(profesorService.updateProfesorById(id, profesor));
    }

    @PostMapping
    public ResponseEntity<ProfesorDto> saveProfesor(@RequestBody Profesor profesor){
        return ResponseEntity.status(HttpStatus.CREATED).body(profesorService.saveProfesor(profesor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable int id) {
        profesorService.deleteProfesorByid(id);
        return ResponseEntity.noContent().build();
    }
}