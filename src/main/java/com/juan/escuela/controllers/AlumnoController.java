package com.juan.escuela.controllers;

import com.juan.escuela.dto.AlumnoDto;
import com.juan.escuela.dto.AlumnoMateriasDto;
import com.juan.escuela.models.Alumno;
import com.juan.escuela.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/alumnos")
    public List<AlumnoDto> getAll() {
        return alumnoService.getAllAlumnos();
    }

    @GetMapping("/alumnos/{id}")
    public AlumnoMateriasDto getAlumno(@PathVariable int id) {
        return alumnoService.getAlumnoById(id);
    }

    @PostMapping("/alumnos")
    public AlumnoDto saveAlumno(@RequestBody Alumno alumno) {
        AlumnoDto alumnoDto = alumnoService.postAlumno(alumno);
        return alumnoDto;
    }

    @DeleteMapping("/alumnos/{id}")
    public void deleteAlumno(@PathVariable int id) {
        alumnoService.deleteAlumnoById(id);
    }
}
