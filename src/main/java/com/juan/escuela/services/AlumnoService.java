package com.juan.escuela.services;

import com.juan.escuela.dto.AlumnoDto;
import com.juan.escuela.dto.AlumnoMateriasDto;
import com.juan.escuela.exceptions.AppException;
import com.juan.escuela.mappers.AlumnoMapper;
import com.juan.escuela.models.Alumno;
import com.juan.escuela.repositories.AlumnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    private final AlumnoMapper alumnoMapper;

    public List<AlumnoDto> getAllAlumnos() {
        List<Alumno> alumnos = (List<Alumno>) alumnoRepository.findAll();
        return alumnoMapper.toListAlumnoDto(alumnos);
    }

    public AlumnoMateriasDto getAlumnoById (int id) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new AppException("no se encontro el alumno con el id: " + id, HttpStatus.NOT_FOUND));

        return alumnoMapper.toAlumnoMateriasDto(alumno);
    }

    public AlumnoDto putAlumno(int id, AlumnoDto newAlumno){
        Alumno alumnoPut = alumnoRepository.findById(id)
                .map(alumno -> {
                    alumno.setId(id);
                    alumno.setNombre(newAlumno.getNombre());
                    alumno.setApellido(newAlumno.getApellido());
                    alumno.setDni(newAlumno.getDni());
                    alumno.setCelular(newAlumno.getCelular());
                    alumno.setTelefono(newAlumno.getTelefono());
                    alumno.setEmail(newAlumno.getEmail());
                    alumno.setFechaNacimiento(newAlumno.getFechaNacimiento());
                    alumno.setDireccion(newAlumno.getDireccion());
                    alumno.setSexo(newAlumno.getSexo());
                    return alumnoRepository.save(alumno);})
                .orElseThrow(() -> new AppException("no se encontro el alumno con el id: " + id, HttpStatus.NOT_FOUND));

        return alumnoMapper.toAlumnoDto(alumnoPut);
    }

    public void deleteAlumnoById (int id) {
        if (!alumnoRepository.existsById(id)) {
            throw new AppException("no se pudo eliminar el alumno porque no se encontro el alumno con el id: " + id, HttpStatus.NOT_FOUND);
        }

        alumnoRepository.deleteById(id);
    }

    public AlumnoDto saveAlumno(AlumnoDto alumnoDto) {
        Alumno alumnoSave = alumnoRepository.save(alumnoMapper.toAlumno(alumnoDto));
        return alumnoMapper.toAlumnoDto(alumnoSave);
    }

}
