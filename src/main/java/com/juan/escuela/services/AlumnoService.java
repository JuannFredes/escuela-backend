package com.juan.escuela.services;

import com.juan.escuela.models.Alumno;
import com.juan.escuela.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<Alumno> getAll(){
        List<Alumno> alumnos = (List<Alumno>) alumnoRepository.findAll();
        return alumnos;
    }
}
