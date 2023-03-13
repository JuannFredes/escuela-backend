package com.juan.escuela.repositories;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.juan.escuela.models.Alumno;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class AlumnoRepositoryTest {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Test
    @DatabaseSetup("/dataset/alumnos.xml")
    @DatabaseSetup("/dataset/materias.xml")
    @DatabaseSetup("/dataset/MATERIAS_ALUMNOS.xml")
    void toFindByIdTest(){
        int id =10;

        Optional<Alumno> alumno = alumnoRepository.findById(id);

        assertAll( () -> {
            assertTrue(alumno.isPresent());
            assertEquals(id, alumno.get().getId());
            assertEquals("67545989", alumno.get().getDni());
            assertEquals(1, alumno.get().getMateriaAlumnos().get(0).getNota());
            assertEquals("materia2", alumno.get().getMateriaAlumnos().get(0).getMateria().getNombre());
            assertEquals(4, alumno.get().getMateriaAlumnos().get(1).getNota());
            assertEquals("materia1", alumno.get().getMateriaAlumnos().get(1).getMateria().getNombre());
        });

    }

    @Test
    @DatabaseSetup("/dataset/alumnos.xml")
    void toFindAllTest(){
        List<Alumno> alumnos = (List<Alumno>) alumnoRepository.findAll();
        assertAll( () -> {
            assertEquals(2, alumnos.size());
            assertEquals(10, alumnos.get(0).getId());
            assertEquals("67545989", alumnos.get(0).getDni());
            assertEquals(20, alumnos.get(1).getId());
            assertEquals("emailAlumno2@gmail.com", alumnos.get(1).getEmail());
        });
    }

    @Test
    @DatabaseSetup("/dataset/alumnos.xml")
    void deleteByIdAndExistByIdTest(){
        int id = 10;

        boolean existAntes = alumnoRepository.existsById(id);
        alumnoRepository.deleteById(id);
        boolean existDespues = alumnoRepository.existsById(id);

        assertAll( () -> {
            assertTrue(existAntes);
            assertFalse(existDespues);
        });
    }

    @Test
    @DatabaseSetup("/dataset/alumnos.xml")
    void saveTest(){
        Alumno alumno = Alumno.builder()
                .nombre("AlumnoNombre")
                .apellido("AlumnoApellido")
                .sexo('O')
                .dni("87507431")
                .email("emailAlumno@hotmail.com")
                .celular("746536465")
                .telefono("65645646")
                .numeroEmergencia("67454353")
                .anio((short)5)
                .division((short)3)
                .direccion("Direccion 123")
                .build();

        Alumno alumnoSave = alumnoRepository.save(alumno);
        boolean exits = alumnoRepository.existsById(alumnoSave.getId());

        assertAll( () -> {
            assertTrue(exits);
            assertNotNull(alumnoSave);
            assertEquals(alumno.getDni(), alumnoSave.getDni());
            assertEquals(alumno.getNumeroEmergencia(), alumnoSave.getNumeroEmergencia());
        });

    }

}
