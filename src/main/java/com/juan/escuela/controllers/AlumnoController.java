package com.juan.escuela.controllers;

import com.juan.escuela.dto.AlumnoDto;
import com.juan.escuela.models.Alumno;
import com.juan.escuela.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/alumnos")
    public List<AlumnoDto> getAll() {
        return alumnoService.getAll();
    }
}
