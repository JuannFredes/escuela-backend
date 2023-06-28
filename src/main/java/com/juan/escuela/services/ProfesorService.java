package com.juan.escuela.services;

import com.juan.escuela.dto.ProfesorDto;
import com.juan.escuela.exceptions.AppException;
import com.juan.escuela.mappers.ProfesorMapper;
import com.juan.escuela.models.Materia;
import com.juan.escuela.models.Profesor;
import com.juan.escuela.repositories.ProfesorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfesorService {

    private final ProfesorRepository profesorRepository;

    private final ProfesorMapper profesorMapper;

    public List<ProfesorDto> getAllProfesor(){
        List<Profesor> profesorDtos = (List<Profesor>) profesorRepository.findAll();
        return profesorMapper.toListProfesorDto(profesorDtos);
    }

    public ProfesorDto getProfesor(int id) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new AppException("el profesor con el id: " + id + " no existe", HttpStatus.BAD_REQUEST));
        return profesorMapper.toProfesorDto(profesor);
    }

    public ProfesorDto saveProfesor(ProfesorDto profesorDto) {
        int idMateria = profesorRepository.getIdFromMateria(profesorDto.getMateriaEncargado());
        Profesor profesor = profesorMapper.toProfesor(profesorDto);
        profesor.setMateria(new Materia(idMateria, profesorDto.getMateriaEncargado()));
        Profesor profesorSave = profesorRepository.save(profesor);
        return profesorMapper.toProfesorDto(profesorSave);
    }

    public ProfesorDto updateProfesor(int id, ProfesorDto newProfesor){
        int idMateria = profesorRepository.getIdFromMateria(newProfesor.getMateriaEncargado());
        Profesor profesorPut = profesorRepository.findById(id)
                .map(profesor -> {
                    profesor.setId(id);
                    profesor.setNombre(newProfesor.getNombre());
                    profesor.setApellido(newProfesor.getApellido());
                    profesor.setDni(newProfesor.getDni());
                    profesor.setCelular(newProfesor.getCelular());
                    profesor.setTelefono(newProfesor.getTelefono());
                    profesor.setEmail(newProfesor.getEmail());
                    profesor.setDireccion(newProfesor.getDireccion());
                    profesor.setSexo(newProfesor.getSexo());
                    profesor.setFechaNacimiento(newProfesor.getFechaNacimiento());
                    profesor.setMateria(new Materia(idMateria, newProfesor.getMateriaEncargado()));
                    return profesorRepository.save(profesor);})
                .orElseThrow(() -> new AppException("no se encontro el alumno con el id: " + id, HttpStatus.NOT_FOUND));

        return profesorMapper.toProfesorDto(profesorPut);
    }

    public void deleteProfesor(int id){
        if (!profesorRepository.existsById(id)) {
            throw new AppException("no se pudo eliminar porque no se encontro el alumno con el id: " + id, HttpStatus.NOT_FOUND);
        }

        profesorRepository.deleteById(id);
    }

}
