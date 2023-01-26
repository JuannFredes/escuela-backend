package com.juan.escuela.services;

import com.juan.escuela.dto.MateriaNotaDto;
import com.juan.escuela.mappers.MateriaMapper;
import com.juan.escuela.models.Alumno;
import com.juan.escuela.models.AlumnoMateriaKeys;
import com.juan.escuela.models.Materia;
import com.juan.escuela.models.MateriaAlumno;
import com.juan.escuela.repositories.MateriaAlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MateriaAlumnoService {
    @Autowired
    private MateriaAlumnoRepository materiaAlumnoRepository;
    @Autowired
    private MateriaMapper materiaMapper;

    public MateriaNotaDto createMateriaNota(int idAlumno, MateriaNotaDto materiaNotaDto) {
        AlumnoMateriaKeys alumnoMateriaKeys = new AlumnoMateriaKeys(materiaNotaDto.getId(),idAlumno);
        MateriaAlumno materiaAlumno = new MateriaAlumno(alumnoMateriaKeys);
        materiaAlumno.setAlumno(new Alumno(idAlumno));
        materiaAlumno.setMateria(new Materia(materiaNotaDto.getId()));
        materiaAlumno.setNota(materiaNotaDto.getNota());
        System.out.println(materiaAlumno);
        MateriaAlumno materiaAlumnoSave = materiaAlumnoRepository.save(materiaAlumno);
        return materiaMapper.toMateriaNotaDto(materiaAlumnoSave);
    }

}
