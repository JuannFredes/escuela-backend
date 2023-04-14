package com.juan.escuela.mappers;

import com.juan.escuela.dto.ProfesorDto;
import com.juan.escuela.models.Materia;
import com.juan.escuela.models.Profesor;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DefaultClassInfoStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class ProfesorMapperTest {
    private ProfesorMapper profesorMapper = new ProfesorMapperImpl();;
    private static PodamFactory podamFactory;
    private static ClassInfoStrategy classInfoStrategy;

    @BeforeAll
    static void setup() {
        podamFactory = new PodamFactoryImpl();
        classInfoStrategy = DefaultClassInfoStrategy.getInstance();
        podamFactory.setClassStrategy(classInfoStrategy);
    }

    @Test
    void toProfesorDtoTest(){
        DefaultClassInfoStrategy.getInstance()
                .addExcludedField(Materia.class, "profesors")
                .addExcludedField(Materia.class, "materiaAlumnos");

        Profesor profesor = podamFactory.manufacturePojo(Profesor.class);

        ProfesorDto profesorDto = profesorMapper.toProfesorDto(profesor);

        assertAll(() -> {
            assertEquals(profesor.getNombre(), profesorDto.getNombre());
            assertEquals(profesor.getApellido(), profesorDto.getApellido());
            assertEquals(profesor.getDni(), profesorDto.getDni());
            assertEquals(profesor.getCelular(), profesorDto.getCelular());
            assertEquals(profesor.getDireccion(), profesorDto.getDireccion());
            assertEquals(profesor.getSexo(), profesorDto.getSexo());
            assertEquals(profesor.getTelefono(), profesorDto.getTelefono());
            assertEquals(Period.between(profesor.getFechaNacimiento(), LocalDate.now()).getYears(), profesorDto.getAge());
        });

        DefaultClassInfoStrategy.getInstance()
                .removeExcludedField(Materia.class, "profesors")
                .removeExcludedField(Materia.class, "materiaAlumnos");
    }

    @Test
    void toProfesorTest(){

        ProfesorDto profesorDto = podamFactory.manufacturePojo(ProfesorDto.class);

        Profesor profesor = profesorMapper.toProfesor(profesorDto);

        assertAll(() -> {
            assertEquals(profesorDto.getNombre(), profesor.getNombre());
            assertEquals(profesorDto.getApellido(), profesor.getApellido());
            assertEquals(profesorDto.getDni(), profesor.getDni());
            assertEquals(profesorDto.getCelular(), profesor.getCelular());
            assertEquals(profesorDto.getDireccion(), profesor.getDireccion());
            assertEquals(profesorDto.getSexo(), profesor.getSexo());
            assertEquals(profesorDto.getTelefono(), profesor.getTelefono());
            assertEquals(profesorDto.getMateriaEncargado(), profesor.getMateria().getNombre());
        });

    }

    @Test
    void toListProfesorDtoTest(){
        DefaultClassInfoStrategy.getInstance()
                .addExcludedField(Materia.class, "profesors")
                .addExcludedField(Materia.class, "materiaAlumnos");

        List<Profesor> profesors = podamFactory.manufacturePojo(ArrayList.class, Profesor.class);

        List<ProfesorDto> profesorDtos = profesorMapper.toListProfesorDto(profesors);

        assertAll(() -> {
            assertEquals(profesors.get(0).getNombre(), profesorDtos.get(0).getNombre());
            assertEquals(profesors.get(0).getApellido(), profesorDtos.get(0).getApellido());
            assertEquals(profesors.get(0).getDni(), profesorDtos.get(0).getDni());
            assertEquals(profesors.get(0).getCelular(), profesorDtos.get(0).getCelular());
            assertEquals(profesors.get(0).getDireccion(), profesorDtos.get(0).getDireccion());
            assertEquals(profesors.get(0).getSexo(), profesorDtos.get(0).getSexo());
            assertEquals(profesors.get(0).getTelefono(), profesorDtos.get(0).getTelefono());
            assertEquals(Period.between(profesors.get(0).getFechaNacimiento(), LocalDate.now()).getYears(), profesorDtos.get(0).getAge());

            assertEquals(profesors.get(1).getNombre(), profesorDtos.get(1).getNombre());
            assertEquals(profesors.get(1).getApellido(), profesorDtos.get(1).getApellido());
            assertEquals(profesors.get(1).getDni(), profesorDtos.get(1).getDni());
            assertEquals(profesors.get(1).getCelular(), profesorDtos.get(1).getCelular());
            assertEquals(profesors.get(1).getDireccion(), profesorDtos.get(1).getDireccion());
            assertEquals(profesors.get(1).getSexo(), profesorDtos.get(1).getSexo());
            assertEquals(profesors.get(1).getTelefono(), profesorDtos.get(1).getTelefono());
            assertEquals(Period.between(profesors.get(1).getFechaNacimiento(), LocalDate.now()).getYears(), profesorDtos.get(1).getAge());

            assertEquals(profesors.get(2).getNombre(), profesorDtos.get(2).getNombre());
            assertEquals(profesors.get(2).getApellido(), profesorDtos.get(2).getApellido());
            assertEquals(profesors.get(2).getDni(), profesorDtos.get(2).getDni());
            assertEquals(profesors.get(2).getCelular(), profesorDtos.get(2).getCelular());
            assertEquals(profesors.get(2).getDireccion(), profesorDtos.get(2).getDireccion());
            assertEquals(profesors.get(2).getSexo(), profesorDtos.get(2).getSexo());
            assertEquals(profesors.get(2).getTelefono(), profesorDtos.get(2).getTelefono());
            assertEquals(Period.between(profesors.get(2).getFechaNacimiento(), LocalDate.now()).getYears(), profesorDtos.get(2).getAge());

            assertEquals(profesors.get(3).getNombre(), profesorDtos.get(3).getNombre());
            assertEquals(profesors.get(3).getApellido(), profesorDtos.get(3).getApellido());
            assertEquals(profesors.get(3).getDni(), profesorDtos.get(3).getDni());
            assertEquals(profesors.get(3).getCelular(), profesorDtos.get(3).getCelular());
            assertEquals(profesors.get(3).getDireccion(), profesorDtos.get(3).getDireccion());
            assertEquals(profesors.get(3).getSexo(), profesorDtos.get(3).getSexo());
            assertEquals(profesors.get(3).getTelefono(), profesorDtos.get(3).getTelefono());
            assertEquals(Period.between(profesors.get(3).getFechaNacimiento(), LocalDate.now()).getYears(), profesorDtos.get(3).getAge());
        });

        DefaultClassInfoStrategy.getInstance()
                .removeExcludedField(Materia.class, "profesors")
                .removeExcludedField(Materia.class, "materiaAlumnos");

    }

}
