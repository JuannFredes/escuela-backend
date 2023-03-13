package com.juan.escuela.mappers;

import com.juan.escuela.dto.AlumnoDto;
import com.juan.escuela.dto.AlumnoMateriasDto;
import com.juan.escuela.dto.MateriaNotaDto;
import com.juan.escuela.models.Alumno;
import static org.junit.jupiter.api.Assertions.*;

import com.juan.escuela.models.Materia;
import com.juan.escuela.models.MateriaAlumno;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uk.co.jemos.podam.api.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class AlumnoMapperTest {
    private AlumnoMapper alumnoMapper = new AlumnoMapperImpl();;

    private static PodamFactory podamFactory;

    private static ClassInfoStrategy classInfoStrategy;

    @BeforeAll
    static void setup(){
        podamFactory = new PodamFactoryImpl();
        classInfoStrategy = DefaultClassInfoStrategy.getInstance();
        podamFactory.setClassStrategy(classInfoStrategy);
    }


    @Test
    void toAlumnoDtoTest(){
        DefaultClassInfoStrategy.getInstance()
                .addExcludedField(Alumno.class, "materiaAlumnos");

        Alumno alumno = podamFactory.manufacturePojo(Alumno.class);

        AlumnoDto alumnoDto = alumnoMapper.toAlumnoDto(alumno);

        assertAll( ()-> {
            assertEquals(alumno.getNombre(), alumnoDto.getNombre());
            assertEquals(alumno.getApellido(), alumnoDto.getApellido());
            assertEquals(alumno.getDni(), alumnoDto.getDni());
            assertEquals(alumno.getNumeroEmergencia(), alumnoDto.getNumeroEmergencia());
            assertEquals(alumno.getEmail(), alumnoDto.getEmail());
            assertEquals(alumno.getDivision(), alumnoDto.getDivision());
            assertEquals(alumno.getCelular(), alumnoDto.getCelular());
            assertEquals(alumno.getAnio(), alumnoDto.getAnio());
            assertEquals(alumno.getSexo(), alumnoDto.getSexo());
            assertEquals(alumno.getTelefono(), alumnoDto.getTelefono());
            assertEquals(Period.between(alumno.getFechaNacimiento(), LocalDate.now()).getYears(), alumnoDto.getAge());
        });

        DefaultClassInfoStrategy.getInstance()
                .removeExcludedField(Alumno.class, "materiaAlumnos");
    }

    @Test
    void toListAlumnoDtoTest() {
        DefaultClassInfoStrategy.getInstance()
                .addExcludedField(Alumno.class, "materiaAlumnos");

        List<Alumno> alumnos = podamFactory.manufacturePojo(ArrayList.class, Alumno.class);

        List<AlumnoDto> alumnoDtos = alumnoMapper.toListAlumnoDto(alumnos);

        assertAll( () -> {
            //Veo, tomando algunos datos, que sean iguales
            assertEquals(alumnos.get(0).getNombre(), alumnoDtos.get(0).getNombre());
            assertEquals(alumnos.get(1).getApellido(), alumnoDtos.get(1).getApellido());
            assertEquals(alumnos.get(2).getNumeroEmergencia(), alumnoDtos.get(2).getNumeroEmergencia());
            //Veo que no sean los mismo datos en el arraylist
            assertNotEquals(alumnos.get(0).getNombre(), alumnoDtos.get(1).getNombre());
            assertNotEquals(alumnos.get(2).getApellido(), alumnoDtos.get(3).getApellido());
            assertNotEquals(alumnos.get(4).getNumeroEmergencia(), alumnoDtos.get(2).getNumeroEmergencia());
        });

        DefaultClassInfoStrategy.getInstance()
                .removeExcludedField(Alumno.class, "materiaAlumnos");
    }

    @Test
    void toMateriaNotaDtoTest() {
        DefaultClassInfoStrategy.getInstance()
                .addExcludedField(MateriaAlumno.class, "alumno")
                .addExcludedField(Materia.class, "profesors")
                .addExcludedField(Materia.class, "materiaAlumnos");

        MateriaAlumno materiaAlumno = podamFactory.manufacturePojo(MateriaAlumno.class);

        MateriaNotaDto materiaNotaDto = alumnoMapper.toMateriaAlumnoDto(materiaAlumno);

        assertAll( () -> {
            assertEquals(materiaAlumno.getMateria().getId(), materiaNotaDto.getId());
            assertEquals(materiaAlumno.getMateria().getNombre(), materiaNotaDto.getNombre());
            assertEquals(materiaAlumno.getNota(), materiaNotaDto.getNota());
        });

        DefaultClassInfoStrategy.getInstance()
                .removeExcludedField(MateriaAlumno.class, "alumno")
                .removeExcludedField(Materia.class, "profesors")
                .removeExcludedField(Materia.class, "materiaAlumnos");
    }

    @Test
    void toListMateriaNotaDtoTest(){
        DefaultClassInfoStrategy.getInstance()
                .addExcludedField(MateriaAlumno.class, "alumno")
                .addExcludedField(Materia.class, "profesors")
                .addExcludedField(Materia.class, "materiaAlumnos");

        List<MateriaAlumno> materiaAlumnos = podamFactory.manufacturePojo(ArrayList.class, MateriaAlumno.class);

        List<MateriaNotaDto> materiaNotaDtos = alumnoMapper.toListMateriaAlumntoDto(materiaAlumnos);

        assertAll( () -> {
            assertEquals(materiaAlumnos.get(0).getMateria().getId(), materiaNotaDtos.get(0).getId());
            assertEquals(materiaAlumnos.get(0).getMateria().getNombre(), materiaNotaDtos.get(0).getNombre());
            assertEquals(materiaAlumnos.get(0).getNota(), materiaNotaDtos.get(0).getNota());

            assertEquals(materiaAlumnos.get(1).getMateria().getId(), materiaNotaDtos.get(1).getId());
            assertEquals(materiaAlumnos.get(1).getMateria().getNombre(), materiaNotaDtos.get(1).getNombre());
            assertEquals(materiaAlumnos.get(1).getNota(), materiaNotaDtos.get(1).getNota());

            assertEquals(materiaAlumnos.get(2).getMateria().getId(), materiaNotaDtos.get(2).getId());
            assertEquals(materiaAlumnos.get(2).getMateria().getNombre(), materiaNotaDtos.get(2).getNombre());
            assertEquals(materiaAlumnos.get(2).getNota(), materiaNotaDtos.get(2).getNota());

            assertEquals(materiaAlumnos.get(3).getMateria().getId(), materiaNotaDtos.get(3).getId());
            assertEquals(materiaAlumnos.get(3).getMateria().getNombre(), materiaNotaDtos.get(3).getNombre());
            assertEquals(materiaAlumnos.get(3).getNota(), materiaNotaDtos.get(3).getNota());
        });

        DefaultClassInfoStrategy.getInstance()
                .removeExcludedField(MateriaAlumno.class, "alumno")
                .removeExcludedField(Materia.class, "profesors")
                .removeExcludedField(Materia.class, "materiaAlumnos");
    }

    @Test
    void toAlumnoMateriasDtoTest(){
        DefaultClassInfoStrategy.getInstance()
                .addExcludedField(MateriaAlumno.class, "alumno")
                .addExcludedField(Materia.class, "profesors")
                .addExcludedField(Materia.class, "materiaAlumnos");

        Alumno alumno = podamFactory.manufacturePojo(Alumno.class);

        AlumnoMateriasDto alumnoMateriasDto = alumnoMapper.toAlumnoMateriasDto(alumno);

        assertAll( () -> {
            assertEquals(alumno.getNombre(), alumnoMateriasDto.getNombre());
            assertEquals(alumno.getApellido(), alumnoMateriasDto.getApellido());
            assertEquals(alumno.getDni(), alumnoMateriasDto.getDni());
            assertEquals(alumno.getNumeroEmergencia(), alumnoMateriasDto.getNumeroEmergencia());
            assertEquals(alumno.getEmail(), alumnoMateriasDto.getEmail());
            assertEquals(Period.between(alumno.getFechaNacimiento(), LocalDate.now()).getYears(), alumnoMateriasDto.getAge());

            assertEquals(alumno.getMateriaAlumnos().get(0).getMateria().getId(), alumnoMateriasDto.getMaterias().get(0).getId());
            assertEquals(alumno.getMateriaAlumnos().get(0).getMateria().getNombre(), alumnoMateriasDto.getMaterias().get(0).getNombre());
            assertEquals(alumno.getMateriaAlumnos().get(0).getNota(), alumnoMateriasDto.getMaterias().get(0).getNota());

            assertEquals(alumno.getMateriaAlumnos().get(1).getMateria().getId(), alumnoMateriasDto.getMaterias().get(1).getId());
            assertEquals(alumno.getMateriaAlumnos().get(1).getMateria().getNombre(), alumnoMateriasDto.getMaterias().get(1).getNombre());
            assertEquals(alumno.getMateriaAlumnos().get(1).getNota(), alumnoMateriasDto.getMaterias().get(1).getNota());

            assertEquals(alumno.getMateriaAlumnos().get(2).getMateria().getId(), alumnoMateriasDto.getMaterias().get(2).getId());
            assertEquals(alumno.getMateriaAlumnos().get(2).getMateria().getNombre(), alumnoMateriasDto.getMaterias().get(2).getNombre());
            assertEquals(alumno.getMateriaAlumnos().get(2).getNota(), alumnoMateriasDto.getMaterias().get(2).getNota());
        });

        DefaultClassInfoStrategy.getInstance()
                .removeExcludedField(MateriaAlumno.class, "alumno")
                .removeExcludedField(Materia.class, "profesors")
                .removeExcludedField(Materia.class, "materiaAlumnos");
    }
}
