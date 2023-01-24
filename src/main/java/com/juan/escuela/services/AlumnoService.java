package com.juan.escuela.services;

import com.juan.escuela.dto.AlumnoDto;
import com.juan.escuela.dto.AlumnoMateriasDto;
import com.juan.escuela.mappers.AlumnoMapper;
import com.juan.escuela.models.Alumno;
import com.juan.escuela.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;
    @Autowired
    private AlumnoMapper alumnoMapper;

    public List<AlumnoDto> getAllAlumnos(){
        List<Alumno> alumnos = (List<Alumno>) alumnoRepository.findAll();
        return alumnoMapper.toListAlumnoDto(alumnos);
    }

    public AlumnoMateriasDto getAlumnoById(int id){
        Alumno alumno = alumnoRepository.findById(id).orElse(null);
        return alumnoMapper.toAlumnoMateriasDto(alumno);
    }

    public void deleteAlumnoById (int id) {
        alumnoRepository.deleteById(id);
    }

    public AlumnoDto postAlumno (Alumno alumno) {
        Alumno alumnoSave = alumnoRepository.save(alumno);
        return alumnoMapper.toAlumnoDto(alumnoSave);
    }

}
