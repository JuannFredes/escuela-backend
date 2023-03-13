package com.juan.escuela.services;

import com.juan.escuela.dto.MateriaNotaDto;
import com.juan.escuela.mappers.MateriaMapper;
import com.juan.escuela.mappers.MateriaMapperImpl;
import com.juan.escuela.models.Alumno;
import com.juan.escuela.models.AlumnoMateriaKeys;
import com.juan.escuela.models.Materia;
import com.juan.escuela.models.MateriaAlumno;
import com.juan.escuela.repositories.MateriaAlumnoRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MateriaAlumnoServiceTest {

    @InjectMocks
    private MateriaAlumnoService materiaAlumnoService;

    @Mock
    private MateriaAlumnoRepository materiaAlumnoRepository;

    @Spy
    private MateriaMapper materiaMapper = new MateriaMapperImpl();

    @Test
    void createPutMateriaNotaTest() {
        MateriaNotaDto materiaNotaDto = MateriaNotaDto.MateriaNotaBuilder()
                        .id(2)
                        .nota((short) 8)
                        .build();
        MateriaAlumno materiaAlumno = MateriaAlumno.builder()
                .alumnoMateriaKeys(new AlumnoMateriaKeys(materiaNotaDto.getId(), 1))
                .alumno(new Alumno(1))
                .materia(new Materia(materiaNotaDto.getId()))
                .nota(materiaNotaDto.getNota())
                .build();

        when(materiaAlumnoRepository.save(any())).thenReturn(materiaAlumno);
        MateriaNotaDto materiaNotaDtoSave = materiaAlumnoService.createPutMateriaNota(1, materiaNotaDto);

        verify(materiaAlumnoRepository).save(materiaAlumno);
        assertAll(() -> {
            assertEquals(materiaAlumno.getNota(), materiaNotaDtoSave.getNota());
            assertEquals(materiaAlumno.getMateria().getId(), materiaNotaDtoSave.getId());
            assertEquals(materiaAlumno.getMateria().getNombre(), materiaNotaDtoSave.getNombre());
        });
    }
}