package com.juan.escuela.services;

import com.juan.escuela.dto.ProfesorDto;
import com.juan.escuela.mappers.ProfesorMapper;
import com.juan.escuela.mappers.ProfesorMapperImpl;
import com.juan.escuela.models.Materia;
import com.juan.escuela.models.Profesor;
import com.juan.escuela.repositories.ProfesorRepository;
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

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProfesorServiceTest {

    @InjectMocks
    private ProfesorService profesorService;

    @Mock
    private ProfesorRepository profesorRepository;

    @Spy
    private ProfesorMapper profesorMapper = new ProfesorMapperImpl();

    private static PodamFactory podamFactory;
    private static ClassInfoStrategy classInfoStrategy;

    @BeforeAll
    static void setup(){
        podamFactory = new PodamFactoryImpl();
        classInfoStrategy = DefaultClassInfoStrategy.getInstance();
        podamFactory.setClassStrategy(classInfoStrategy);
    }

    @Test
    void getAllProfesoresTest(){
        DefaultClassInfoStrategy.getInstance()
                .addExcludedField(Materia.class, "profesors")
                .addExcludedField(Materia.class, "materiaAlumnos");

        List<Profesor> profesors = podamFactory.manufacturePojo(ArrayList.class, Profesor.class);
        when(profesorRepository.findAll()).thenReturn(profesors);

        List<ProfesorDto> profesorDtos = profesorService.getAllProfesor();
        verify(profesorRepository).findAll();

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

    @Test
    void getProfesorByIdTest(){
        DefaultClassInfoStrategy.getInstance()
                .addExcludedField(Materia.class, "profesors")
                .addExcludedField(Materia.class, "materiaAlumnos");

        Profesor profesor = podamFactory.manufacturePojo(Profesor.class);
        when(profesorRepository.findById(anyInt())).thenReturn(Optional.of(profesor));

        ProfesorDto profesorDto = profesorService.getProfesor(anyInt());
        verify(profesorRepository).findById(anyInt());

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
    void updateProfesorTest(){

        ProfesorDto profesorDto = podamFactory.manufacturePojo(ProfesorDto.class);
        Profesor profesor = profesorMapper.toProfesor(profesorDto);

        when(profesorRepository.findById(profesorDto.getId())).thenReturn(Optional.of(profesor));
        when(profesorRepository.save(profesor)).thenReturn(profesor);

        ProfesorDto profesorResponse = profesorService.updateProfesor(profesorDto.getId(), profesorDto);
        verify(profesorRepository).save(profesor);
        verify(profesorRepository).findById(profesorDto.getId());

        assertAll(() -> {
            assertEquals(profesorDto.getNombre(), profesorResponse.getNombre());
            assertEquals(profesorDto.getApellido(), profesorResponse.getApellido());
            assertEquals(profesorDto.getDni(), profesorResponse.getDni());
            assertEquals(profesorDto.getCelular(), profesorResponse.getCelular());
            assertEquals(profesorDto.getDireccion(), profesorResponse.getDireccion());
            assertEquals(profesorDto.getSexo(), profesorResponse.getSexo());
            assertEquals(profesorDto.getTelefono(), profesorResponse.getTelefono());
        });

    }
    @Test
    void saveProfesorTest(){

        ProfesorDto profesorDto = podamFactory.manufacturePojo(ProfesorDto.class);
        Profesor profesor = profesorMapper.toProfesor(profesorDto);

        when(profesorRepository.save(profesor)).thenReturn(profesor);

        ProfesorDto profesorResponse = profesorService.saveProfesor(profesorDto);
        verify(profesorRepository).save(profesor);

        assertAll(() -> {
            assertEquals(profesorDto.getNombre(), profesorResponse.getNombre());
            assertEquals(profesorDto.getApellido(), profesorResponse.getApellido());
            assertEquals(profesorDto.getDni(), profesorResponse.getDni());
            assertEquals(profesorDto.getCelular(), profesorResponse.getCelular());
            assertEquals(profesorDto.getDireccion(), profesorResponse.getDireccion());
            assertEquals(profesorDto.getSexo(), profesorResponse.getSexo());
            assertEquals(profesorDto.getTelefono(), profesorResponse.getTelefono());
        });

    }

    @Test
    void deleteProfesorByIdTest(){

        when(profesorRepository.existsById(anyInt())).thenReturn(true);
        profesorService.deleteProfesor(anyInt());

        verify(profesorRepository).existsById(anyInt());
        verify(profesorRepository).deleteById(anyInt());
    }

}
