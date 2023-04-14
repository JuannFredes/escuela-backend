package com.juan.escuela.services;

import com.juan.escuela.dto.AlumnoDto;
import com.juan.escuela.dto.MateriaDetailsDto;
import com.juan.escuela.dto.MateriaDto;
import com.juan.escuela.dto.ProfesorDto;
import com.juan.escuela.mappers.*;
import com.juan.escuela.models.Alumno;
import com.juan.escuela.models.Materia;
import com.juan.escuela.models.MateriaAlumno;
import com.juan.escuela.models.Profesor;
import com.juan.escuela.repositories.MateriaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DefaultClassInfoStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MateriaServiceTest {

    @InjectMocks
    private MateriaService materiaService;

    @Mock
    private MateriaRepository materiaRepository;

    @InjectMocks
    private MateriaMapper materiaMapper = spy(MateriaMapperImpl.class);

    @Spy
    private AlumnoMapper alumnoMapper = new AlumnoMapperImpl();

    @Spy
    private ProfesorMapper profesorMapper = new ProfesorMapperImpl();

    private static PodamFactory podamFactory;
    private static ClassInfoStrategy classInfoStrategy;

    @BeforeAll
    static void setUp() {
        podamFactory = new PodamFactoryImpl();
        classInfoStrategy = DefaultClassInfoStrategy.getInstance();
        podamFactory.setClassStrategy(classInfoStrategy);
    }

    @Test
    void getAllMateriasTest() {
        DefaultClassInfoStrategy.getInstance()
                .addExcludedField(Materia.class, "profesors")
                .addExcludedField(Materia.class, "materiaAlumnos");

        List<Materia> materias = podamFactory.manufacturePojo(ArrayList.class, Materia.class);
        when(materiaRepository.findAll()).thenReturn(materias);

        List<MateriaDto> materiaDtos = materiaService.getAllMaterias();
        verify(materiaRepository).findAll();

        assertAll(() -> {
            assertEquals(materias.get(0).getId(), materiaDtos.get(0).getId());
            assertEquals(materias.get(0).getNombre(), materiaDtos.get(0).getNombre());

            assertEquals(materias.get(1).getId(), materiaDtos.get(1).getId());
            assertEquals(materias.get(1).getNombre(), materiaDtos.get(1).getNombre());

            assertEquals(materias.get(2).getId(), materiaDtos.get(2).getId());
            assertEquals(materias.get(2).getNombre(), materiaDtos.get(2).getNombre());

            assertEquals(materias.get(3).getId(), materiaDtos.get(3).getId());
            assertEquals(materias.get(3).getNombre(), materiaDtos.get(3).getNombre());
        });

        DefaultClassInfoStrategy.getInstance()
                .removeExcludedField(Materia.class, "profesors")
                .removeExcludedField(Materia.class, "materiaAlumnos");
    }

    @Test
    void getMateriaByIdTest() {
        DefaultClassInfoStrategy.getInstance()
                .addExcludedField(MateriaAlumno.class, "materia")
                .addExcludedField(Alumno.class, "materiaAlumnos")
                .addExcludedField(Profesor.class, "materia");

        Materia materia = podamFactory.manufacturePojo(Materia.class);
        when(materiaRepository.findById(anyInt())).thenReturn(Optional.of(materia));
        List<ProfesorDto> profesorDtos = profesorMapper.toListProfesorDto(materia.getProfesors());
        List<AlumnoDto> alumnoDtos = alumnoMapper.toListAlumnoDto(materiaMapper.toListAlumno(materia.getMateriaAlumnos()));


        MateriaDetailsDto materiaDetailsDto = materiaService.getMateriaById(anyInt());
        verify(materiaRepository).findById(anyInt());

        assertAll(() -> {
            assertEquals(materia.getId(), materiaDetailsDto.getId());
            assertEquals(materia.getNombre(), materiaDetailsDto.getNombre());
            assertEquals(profesorDtos, materiaDetailsDto.getProfesors());
            assertEquals(alumnoDtos, materiaDetailsDto.getAlumnos());
            assertEquals(alumnoDtos.size(), materiaDetailsDto.getCantidadAlumnos());
        });

        DefaultClassInfoStrategy.getInstance()
                .removeExcludedField(MateriaAlumno.class, "materia")
                .removeExcludedField(Alumno.class, "materiaAlumnos")
                .removeExcludedField(Profesor.class, "materia");
    }

    @Test
    void putMateria() {

        MateriaDto materiaDto = MateriaDto.builder()
                .id(2)
                .nombre("Lengua")
                .build();
        Materia materia = materiaMapper.toMateria(materiaDto);

        when(materiaRepository.findById(materiaDto.getId())).thenReturn(Optional.of(materia));
        when(materiaRepository.save(materia)).thenReturn(materia);

        MateriaDto materiaResponse = materiaService.putMateria(materia.getId(), materiaDto);
        verify(materiaRepository).save(materia);
        verify(materiaRepository).findById(materiaDto.getId());

        assertEquals(materiaDto.getId(), materiaResponse.getId());
        assertEquals(materiaDto.getNombre(), materiaResponse.getNombre());

    }
    @Test
    void saveMateria() {

        MateriaDto materiaDto = MateriaDto.builder()
                .id(2)
                .nombre("Lengua")
                .build();
        Materia materia = materiaMapper.toMateria(materiaDto);
        when(materiaRepository.save(materia)).thenReturn(materia);

        MateriaDto materiaResponse = materiaService.saveMateria(materiaDto);
        verify(materiaRepository).save(materia);

        assertEquals(materiaDto.getId(), materiaResponse.getId());
        assertEquals(materiaDto.getNombre(), materiaResponse.getNombre());

    }

    @Test
    void deleteMateriaById() {
        when(materiaRepository.existsById(anyInt())).thenReturn(true);
        materiaService.deleteMateriaById(anyInt());

        verify(materiaRepository).existsById(anyInt());
        verify(materiaRepository).deleteById(anyInt());

    }
}