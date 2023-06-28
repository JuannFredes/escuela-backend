package com.juan.escuela.services;

import com.juan.escuela.dto.AlumnoDto;
import com.juan.escuela.dto.AlumnoMateriasDto;
import com.juan.escuela.mappers.AlumnoMapper;
import com.juan.escuela.mappers.AlumnoMapperImpl;
import com.juan.escuela.models.Alumno;
import com.juan.escuela.models.Materia;
import com.juan.escuela.models.MateriaAlumno;
import com.juan.escuela.repositories.AlumnoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
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

@ExtendWith(MockitoExtension.class)
public class AlumnoServiceTest {

    @InjectMocks
    private AlumnoService alumnoService;

    @Mock
    private AlumnoRepository alumnoRepository;

    @Spy
    private AlumnoMapper alumnoMapper = new AlumnoMapperImpl();

    private static PodamFactory podamFactory;

    private static ClassInfoStrategy classInfoStrategy;

    @BeforeAll
    static void setup(){
        podamFactory = new PodamFactoryImpl();
        classInfoStrategy = DefaultClassInfoStrategy.getInstance();
        podamFactory.setClassStrategy(classInfoStrategy);
    }

    @Test
    void getAllAlumnos() {
        DefaultClassInfoStrategy.getInstance()
                .addExcludedField(Alumno.class, "materiaAlumnos");

        List<Alumno> alumnos = podamFactory.manufacturePojo(ArrayList.class, Alumno.class);
        when(alumnoRepository.findAll()).thenReturn(alumnos);

        List<AlumnoDto> alumnoDtos = alumnoService.getAllAlumno();

        verify(alumnoRepository).findAll();
        assertAll(() -> {
            assertEquals(alumnos.get(0).getNombre(), alumnoDtos.get(0).getNombre());
            assertEquals(alumnos.get(0).getApellido(), alumnoDtos.get(0).getApellido());
            assertEquals(alumnos.get(0).getDni(), alumnoDtos.get(0).getDni());
            assertEquals(alumnos.get(0).getNumeroEmergencia(), alumnoDtos.get(0).getNumeroEmergencia());
            assertEquals(alumnos.get(0).getEmail(), alumnoDtos.get(0).getEmail());
            assertEquals(alumnos.get(0).getDivision(), alumnoDtos.get(0).getDivision());
            assertEquals(alumnos.get(0).getCelular(), alumnoDtos.get(0).getCelular());
            assertEquals(alumnos.get(0).getAnio(), alumnoDtos.get(0).getAnio());
            assertEquals(alumnos.get(0).getSexo(), alumnoDtos.get(0).getSexo());
            assertEquals(alumnos.get(0).getTelefono(), alumnoDtos.get(0).getTelefono());
            assertEquals(Period.between(alumnos.get(0).getFechaNacimiento(), LocalDate.now()).getYears(), alumnoDtos.get(0).getEdad());

            assertEquals(alumnos.get(1).getNombre(), alumnoDtos.get(1).getNombre());
            assertEquals(alumnos.get(1).getApellido(), alumnoDtos.get(1).getApellido());
            assertEquals(alumnos.get(1).getDni(), alumnoDtos.get(1).getDni());
            assertEquals(alumnos.get(1).getNumeroEmergencia(), alumnoDtos.get(1).getNumeroEmergencia());
            assertEquals(alumnos.get(1).getEmail(), alumnoDtos.get(1).getEmail());
            assertEquals(alumnos.get(1).getDivision(), alumnoDtos.get(1).getDivision());
            assertEquals(alumnos.get(1).getCelular(), alumnoDtos.get(1).getCelular());
            assertEquals(alumnos.get(1).getAnio(), alumnoDtos.get(1).getAnio());
            assertEquals(alumnos.get(1).getSexo(), alumnoDtos.get(1).getSexo());
            assertEquals(alumnos.get(1).getTelefono(), alumnoDtos.get(1).getTelefono());
            assertEquals(Period.between(alumnos.get(1).getFechaNacimiento(), LocalDate.now()).getYears(), alumnoDtos.get(1).getEdad());

            assertEquals(alumnos.get(2).getNombre(), alumnoDtos.get(2).getNombre());
            assertEquals(alumnos.get(2).getApellido(), alumnoDtos.get(2).getApellido());
            assertEquals(alumnos.get(2).getDni(), alumnoDtos.get(2).getDni());
            assertEquals(alumnos.get(2).getNumeroEmergencia(), alumnoDtos.get(2).getNumeroEmergencia());
            assertEquals(alumnos.get(2).getEmail(), alumnoDtos.get(2).getEmail());
            assertEquals(alumnos.get(2).getDivision(), alumnoDtos.get(2).getDivision());
            assertEquals(alumnos.get(2).getCelular(), alumnoDtos.get(2).getCelular());
            assertEquals(alumnos.get(2).getAnio(), alumnoDtos.get(2).getAnio());
            assertEquals(alumnos.get(2).getSexo(), alumnoDtos.get(2).getSexo());
            assertEquals(alumnos.get(2).getTelefono(), alumnoDtos.get(2).getTelefono());
            assertEquals(Period.between(alumnos.get(2).getFechaNacimiento(), LocalDate.now()).getYears(), alumnoDtos.get(2).getEdad());

            assertEquals(alumnos.get(3).getNombre(), alumnoDtos.get(3).getNombre());
            assertEquals(alumnos.get(3).getApellido(), alumnoDtos.get(3).getApellido());
            assertEquals(alumnos.get(3).getDni(), alumnoDtos.get(3).getDni());
            assertEquals(alumnos.get(3).getNumeroEmergencia(), alumnoDtos.get(3).getNumeroEmergencia());
            assertEquals(alumnos.get(3).getEmail(), alumnoDtos.get(3).getEmail());
            assertEquals(alumnos.get(3).getDivision(), alumnoDtos.get(3).getDivision());
            assertEquals(alumnos.get(3).getCelular(), alumnoDtos.get(3).getCelular());
            assertEquals(alumnos.get(3).getAnio(), alumnoDtos.get(3).getAnio());
            assertEquals(alumnos.get(3).getSexo(), alumnoDtos.get(3).getSexo());
            assertEquals(alumnos.get(3).getTelefono(), alumnoDtos.get(3).getTelefono());
            assertEquals(Period.between(alumnos.get(3).getFechaNacimiento(), LocalDate.now()).getYears(), alumnoDtos.get(3).getEdad());
        });

        DefaultClassInfoStrategy.getInstance()
                .removeExcludedField(Alumno.class, "materiaAlumnos");
    }

    @Test
    void getAlumnoByIdTest(){
        DefaultClassInfoStrategy.getInstance()
                .addExcludedField(MateriaAlumno.class, "alumno")
                .addExcludedField(Materia.class, "profesors")
                .addExcludedField(Materia.class, "materiaAlumnos");

        Alumno alumno = podamFactory.manufacturePojo(Alumno.class);
        when(alumnoRepository.findById(1)).thenReturn(Optional.of(alumno));

        AlumnoMateriasDto alumnoMateriasDto = alumnoService.getAlumno(1);
        verify(alumnoRepository).findById(1);

        assertAll(() -> {
            assertAll( () -> {
                assertEquals(alumno.getNombre(), alumnoMateriasDto.getNombre());
                assertEquals(alumno.getApellido(), alumnoMateriasDto.getApellido());
                assertEquals(alumno.getDni(), alumnoMateriasDto.getDni());
                assertEquals(alumno.getNumeroEmergencia(), alumnoMateriasDto.getNumeroEmergencia());
                assertEquals(alumno.getEmail(), alumnoMateriasDto.getEmail());
                assertEquals(Period.between(alumno.getFechaNacimiento(), LocalDate.now()).getYears(), alumnoMateriasDto.getEdad());

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
        });

        DefaultClassInfoStrategy.getInstance()
                .removeExcludedField(MateriaAlumno.class, "alumno")
                .removeExcludedField(Materia.class, "profesors")
                .removeExcludedField(Materia.class, "materiaAlumnos");
    }

    @Test
    void deleteAlumnoByIdTest(){
        when(alumnoRepository.existsById(anyInt())).thenReturn(true);
        alumnoService.deleteAlumno(anyInt());

        verify(alumnoRepository).existsById(anyInt());
        verify(alumnoRepository).deleteById(anyInt());
    }

    @Test
    void saveAlumnoTest(){

        AlumnoDto alumnoDto = podamFactory.manufacturePojo(AlumnoDto.class);
        Alumno alumno = alumnoMapper.toAlumno(alumnoDto);

        when(alumnoRepository.save(alumno)).thenReturn(alumno);

        AlumnoDto alumnoResponse = alumnoService.saveAlumno(alumnoDto);
        verify(alumnoRepository).save(alumno);

        assertAll( ()-> {
            assertEquals(alumnoDto.getNombre(), alumnoResponse.getNombre());
            assertEquals(alumnoDto.getApellido(), alumnoResponse.getApellido());
            assertEquals(alumnoDto.getDni(), alumnoResponse.getDni());
            assertEquals(alumnoDto.getNumeroEmergencia(), alumnoResponse.getNumeroEmergencia());
            assertEquals(alumnoDto.getEmail(), alumnoResponse.getEmail());
            assertEquals(alumnoDto.getDivision(), alumnoResponse.getDivision());
            assertEquals(alumnoDto.getCelular(), alumnoResponse.getCelular());
            assertEquals(alumnoDto.getAnio(), alumnoResponse.getAnio());
            assertEquals(alumnoDto.getSexo(), alumnoResponse.getSexo());
            assertEquals(alumnoDto.getTelefono(), alumnoResponse.getTelefono());
        });

    }

    @Test
    void putAlumnoTest(){

        AlumnoDto alumnoDto = podamFactory.manufacturePojo(AlumnoDto.class);
        Alumno alumno = alumnoMapper.toAlumno(alumnoDto);

        when(alumnoRepository.findById(alumnoDto.getId())).thenReturn(Optional.of(alumno));
        when(alumnoRepository.save(alumno)).thenReturn(alumno);

        AlumnoDto alumnoResponse = alumnoService.updateAlumno(alumnoDto.getId(), alumnoDto);
        verify(alumnoRepository).save(alumno);
        verify(alumnoRepository).findById(alumnoDto.getId());

        assertAll( ()-> {
            assertEquals(alumnoDto.getNombre(), alumnoResponse.getNombre());
            assertEquals(alumnoDto.getApellido(), alumnoResponse.getApellido());
            assertEquals(alumnoDto.getDni(), alumnoResponse.getDni());
            assertEquals(alumnoDto.getNumeroEmergencia(), alumnoResponse.getNumeroEmergencia());
            assertEquals(alumnoDto.getEmail(), alumnoResponse.getEmail());
            assertEquals(alumnoDto.getDivision(), alumnoResponse.getDivision());
            assertEquals(alumnoDto.getCelular(), alumnoResponse.getCelular());
            assertEquals(alumnoDto.getAnio(), alumnoResponse.getAnio());
            assertEquals(alumnoDto.getSexo(), alumnoResponse.getSexo());
            assertEquals(alumnoDto.getTelefono(), alumnoResponse.getTelefono());
        });

    }
}
