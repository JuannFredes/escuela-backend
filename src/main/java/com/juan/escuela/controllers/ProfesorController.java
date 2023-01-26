package com.juan.escuela.controllers;

import com.juan.escuela.dto.ProfesorDto;
import com.juan.escuela.models.Profesor;
import com.juan.escuela.services.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping("/profesor")
    public List<ProfesorDto> getAllProfesor(){
        return profesorService.getAllProfesores();
    }

    @GetMapping("/profesor/{id}")
    public ProfesorDto getProfesor(@PathVariable int id){
        return profesorService.getProfesorById(id);
    }

    @PostMapping("/profesor")
    public ProfesorDto saveProfesor(@RequestBody Profesor profesor){
        return profesorService.saveProfesor(profesor);
    }

    @DeleteMapping("/profesor/{id}")
    public void deleteProfesor(@PathVariable int id) {
        profesorService.deleteProfesorByid(id);
    }
}
