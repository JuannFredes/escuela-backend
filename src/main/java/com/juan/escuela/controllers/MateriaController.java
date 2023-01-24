package com.juan.escuela.controllers;

import com.juan.escuela.dto.MateriaDetailsDto;
import com.juan.escuela.dto.MateriaDto;
import com.juan.escuela.models.Materia;
import com.juan.escuela.services.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping("/materias")
    public List<MateriaDto> getAll(){
        return materiaService.getAllMaterias();
    }

    @GetMapping("/materias/{id}")
    public MateriaDetailsDto getMateria(@PathVariable int id) {
        return materiaService.getMateriaById(id);
    }

    @PostMapping("/materias")
    public MateriaDto saveMateria(@RequestBody Materia materia) {
        return materiaService.saveMateria(materia);
    }

    @DeleteMapping("/materias/{id}")
    public void deleteMateria(@PathVariable int id){
        materiaService.deleteMateriaById(id);
    }
}
