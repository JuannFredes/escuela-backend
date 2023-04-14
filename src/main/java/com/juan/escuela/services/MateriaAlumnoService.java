package com.juan.escuela.services;

import com.juan.escuela.dto.MateriaNotaDto;
import com.juan.escuela.exceptions.AppException;
import com.juan.escuela.mappers.MateriaMapper;
import com.juan.escuela.models.Alumno;
import com.juan.escuela.models.AlumnoMateriaKeys;
import com.juan.escuela.models.Materia;
import com.juan.escuela.models.MateriaAlumno;
import com.juan.escuela.repositories.MateriaAlumnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MateriaAlumnoService {

    private final MateriaAlumnoRepository materiaAlumnoRepository;

    private final MateriaMapper materiaMapper;

    public MateriaNotaDto createPutMateriaNota(int idAlumno, int idMateria, MateriaNotaDto materiaNotaDto) {
        if (materiaAlumnoRepository.alumnoMateriaExistsById(idMateria, idAlumno) == 0) {
            throw new AppException("no se ha encontrado el alumno con el id: " + idAlumno + " o la materia con el id: " + idMateria, HttpStatus.BAD_REQUEST);
        }

        AlumnoMateriaKeys alumnoMateriaKeys = new AlumnoMateriaKeys(idMateria,idAlumno);
        /*MateriaAlumno materiaAlumno = materiaAlumnoRepository.findById(alumnoMateriaKeys)
                .orElseThrow(() -> new AppException("no se ha encontrado el alumno con el id: " + idAlumno + " o la materia con el id: " + idMateria, HttpStatus.BAD_REQUEST));
*/
        MateriaAlumno materiaAlumno = MateriaAlumno.builder()
                .alumnoMateriaKeys(alumnoMateriaKeys)
                .alumno(new Alumno(idAlumno))
                .materia(new Materia(idMateria))
                .nota(materiaNotaDto.getNota())
                .build();
        MateriaAlumno materiaAlumnoSave = materiaAlumnoRepository.save(materiaAlumno);
        return materiaMapper.toMateriaNotaDto(materiaAlumnoSave);
    }

}
