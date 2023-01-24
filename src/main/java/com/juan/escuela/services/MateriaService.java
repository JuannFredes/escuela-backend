package com.juan.escuela.services;

import com.juan.escuela.dto.MateriaDetailsDto;
import com.juan.escuela.dto.MateriaDto;
import com.juan.escuela.mappers.MateriaMapper;
import com.juan.escuela.models.Materia;
import com.juan.escuela.repositories.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private MateriaMapper materiaMapper;

    public List<MateriaDto> getAllMaterias() {
        List<Materia> materias = (List<Materia>) materiaRepository.findAll();
        return materiaMapper.toListMateriaDto(materias);
    }

    public MateriaDetailsDto getMateriaById(int id) {
        Materia materia = materiaRepository.findById(id).orElse(null);
        return materiaMapper.toMateriaDetailsDto(materia);
    }

    public MateriaDto saveMateria(Materia materia) {
        Materia materiaSave = materiaRepository.save(materia);
        return materiaMapper.toMateriaDto(materia);
    }

    public void deleteMateriaById(int id){
        materiaRepository.deleteById(id);
    }

}