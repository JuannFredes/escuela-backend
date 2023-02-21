package com.juan.escuela.services;

import com.juan.escuela.dto.MateriaDetailsDto;
import com.juan.escuela.dto.MateriaDto;
import com.juan.escuela.exceptions.AppException;
import com.juan.escuela.mappers.MateriaMapper;
import com.juan.escuela.models.Materia;
import com.juan.escuela.repositories.MateriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MateriaService {

    private final MateriaRepository materiaRepository;

    private final MateriaMapper materiaMapper;

    public List<MateriaDto> getAllMaterias() {
        List<Materia> materias = (List<Materia>) materiaRepository.findAll();
        return materiaMapper.toListMateriaDto(materias);
    }

    public MateriaDetailsDto getMateriaById(int id) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new AppException("la materia con el id: " + id + " no se encuentra", HttpStatus.BAD_REQUEST));
        return materiaMapper.toMateriaDetailsDto(materia);
    }

    public MateriaDto saveMateria(Materia materia) {
        Materia materiaSave = materiaRepository.save(materia);
        return materiaMapper.toMateriaDto(materia);
    }

    public void deleteMateriaById(int id){
        if(!materiaRepository.existsById(id)) {
            throw new AppException("la materia con el id: " + id + " no existe", HttpStatus.BAD_REQUEST);
        }

        materiaRepository.deleteById(id);
    }

    public MateriaDto putMateriaById(int id, Materia newMateria){
        Materia materiaPut = materiaRepository.findById(id)
                .map(materia -> {
                    materia.setNombre(newMateria.getNombre());
                    return materiaRepository.save(materia);})
                .orElseThrow(() -> new AppException("no se ha podido actualizar la materia, uno de los datos enviados es incorrecto", HttpStatus.BAD_REQUEST));

        return materiaMapper.toMateriaDto(materiaPut);
    }

}
