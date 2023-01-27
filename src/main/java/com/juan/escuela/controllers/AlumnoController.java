package com.juan.escuela.controllers;

import com.juan.escuela.dto.AlumnoDto;
import com.juan.escuela.dto.AlumnoMateriasDto;
import com.juan.escuela.models.Alumno;
import com.juan.escuela.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<List<AlumnoDto>> getAlumnos(){
        return ResponseEntity.ok(alumnoService.getAllAlumnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoMateriasDto> getAlumno(@PathVariable int id) {
        return ResponseEntity.ok(alumnoService.getAlumnoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlumnoDto> updateAlumno(@PathVariable int id, @RequestBody Alumno alumno){
        return ResponseEntity.ok(alumnoService.putAlumnoById(id,alumno));
    }

    @PostMapping
    public ResponseEntity<AlumnoDto> saveAlumno(@RequestBody @Valid Alumno alumno) {
        AlumnoDto alumnoDto = alumnoService.saveAlumno(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable int id) {
        alumnoService.deleteAlumnoById(id);
        return ResponseEntity.noContent().build();
    }

}
