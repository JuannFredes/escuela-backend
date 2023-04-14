package com.juan.escuela.controllers;

import com.juan.escuela.dto.AlumnoDto;
import com.juan.escuela.dto.AlumnoMateriasDto;
import com.juan.escuela.services.AlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<List<AlumnoDto>> getAll(){
        return ResponseEntity.ok(alumnoService.getAllAlumnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoMateriasDto> getAlumno(@PathVariable int id) {
        return ResponseEntity.ok(alumnoService.getAlumnoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlumnoDto> updateAlumno(@PathVariable int id,@RequestBody AlumnoDto alumnoDto){
        return ResponseEntity.ok(alumnoService.putAlumno(id, alumnoDto));
    }

    @PostMapping
    public ResponseEntity<AlumnoDto> saveAlumno(@RequestBody @Valid AlumnoDto alumno) {
        AlumnoDto alumnoDto = alumnoService.saveAlumno(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable int id) {
        alumnoService.deleteAlumnoById(id);
        return ResponseEntity.noContent().build();
    }

}
