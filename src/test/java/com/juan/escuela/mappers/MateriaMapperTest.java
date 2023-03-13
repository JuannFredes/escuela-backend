package com.juan.escuela.mappers;

import com.juan.escuela.dto.*;
import com.juan.escuela.models.Alumno;
import com.juan.escuela.models.Materia;
import com.juan.escuela.models.MateriaAlumno;
import static org.junit.jupiter.api.Assertions.*;

import com.juan.escuela.models.Profesor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DefaultClassInfoStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class) // JUnit 5
@ContextConfiguration(classes = {
        MateriaMapperImpl.class,
        AlumnoMapperImpl.class,
        ProfesorMapperImpl.class
})
public class MateriaMapperTest {

    @Autowired
    private MateriaMapper materiaMapper;

    private static PodamFactory podamFactory;

    private static ClassInfoStrategy classInfoStrategy;

    @BeforeAll
    static void setup(){
        podamFactory = new PodamFactoryImpl();
        classInfoStrategy = DefaultClassInfoStrategy.getInstance();
        podamFactory.setClassStrategy(classInfoStrategy);
    }

    @Test
    void toMateriaDtoTest(){

        Materia materia = Materia.builder()
                .id(2)
                .nombre("Lengua")
                .build();

        MateriaDto materiaDto = materiaMapper.toMateriaDto(materia);

        assertEquals(materia.getId(), materiaDto.getId());
        assertEquals(materia.getNombre(), materiaDto.getNombre());
    }

    @Test
    void toListMateriaDtoTest(){

        DefaultClassInfoStrategy.getInstance()
                .addExcludedField(Materia.class, "profesors")
                .addExcludedField(Materia.class, "materiaAlumnos");

        List<Materia> materias = podamFactory.manufacturePojo(ArrayList.class, Materia.class);

        List<MateriaDto> materiaDtos = materiaMapper.toListMateriaDto(materias);

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
    void toAlumnoTest(){

        DefaultClassInfoStrategy.getInstance()
                .addExcludedField(MateriaAlumno.class, "materia")
                .addExcludedField(Alumno.class, "materiaAlumnos");

        MateriaAlumno materiaAlumno = podamFactory.manufacturePojo(MateriaAlumno.class);

        Alumno alumno = materiaMapper.toAlumno(materiaAlumno);

        assertAll(() -> {
            assertEquals(materiaAlumno.getAlumno().getNombre(), alumno.getNombre());
            assertEquals(materiaAlumno.getAlumno().getApellido(), alumno.getApellido());
            assertEquals(materiaAlumno.getAlumno().getDni(), alumno.getDni());
            assertEquals(materiaAlumno.getAlumno().getNumeroEmergencia(), alumno.getNumeroEmergencia());
            assertEquals(materiaAlumno.getAlumno().getDivision(), alumno.getDivision());
            assertEquals(materiaAlumno.getAlumno().getNumeroEmergencia(), alumno.getNumeroEmergencia());
            assertEquals(materiaAlumno.getAlumno().getEmail(), alumno.getEmail());
            assertEquals(materiaAlumno.getAlumno().getCelular(), alumno.getCelular());
            assertEquals(materiaAlumno.getAlumno().getMateriaAlumnos(), alumno.getMateriaAlumnos());
            assertEquals(materiaAlumno.getAlumno().getFechaNacimiento(), alumno.getFechaNacimiento());
        });

        DefaultClassInfoStrategy.getInstance()
                .removeExcludedField(MateriaAlumno.class, "materia")
                .removeExcludedField(Alumno.class, "materiaAlumnos");
    }

    @Test
    void toMateriasDetailsDtoTest() {

        DefaultClassInfoStrategy.getInstance()
                .addExcludedField(MateriaAlumno.class, "materia")
                .addExcludedField(Alumno.class, "materiaAlumnos")
                .addExcludedField(Profesor.class, "materia");

        Materia materia = podamFactory.manufacturePojo(Materia.class);
        ProfesorMapper profesorMapper = new ProfesorMapperImpl();
        AlumnoMapper alumnoMapper = new AlumnoMapperImpl();
        List<ProfesorDto> profesorDtos = profesorMapper.toListProfesorDto(materia.getProfesors());
        List<AlumnoDto> alumnoDtos = alumnoMapper.toListAlumnoDto(materiaMapper.toListAlumno(materia.getMateriaAlumnos()));


        MateriaDetailsDto materiaDetailsDto = materiaMapper.toMateriaDetailsDto(materia);

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
    void toMateriaNotaDtoTest() {
        MateriaAlumno materiaAlumno = MateriaAlumno.builder()
                .nota((short)6)
                .materia(
                        Materia.builder()
                                .id(545)
                                .nombre("MateriaNombre")
                                .build()
                ).build();

        MateriaNotaDto materiaNotaDto = materiaMapper.toMateriaNotaDto(materiaAlumno);

        assertAll(() -> {
            assertEquals(materiaAlumno.getNota(), materiaNotaDto.getNota());
            assertEquals(materiaAlumno.getMateria().getId(), materiaNotaDto.getId());
            assertEquals(materiaAlumno.getMateria().getNombre(), materiaNotaDto.getNombre());
        });

    }
}
