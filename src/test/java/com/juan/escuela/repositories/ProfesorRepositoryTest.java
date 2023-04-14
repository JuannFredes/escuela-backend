package com.juan.escuela.repositories;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.juan.escuela.models.Alumno;
import com.juan.escuela.models.Profesor;
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
public class ProfesorRepositoryTest {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Test
    @DatabaseSetup("/dataset/materias.xml")
    @DatabaseSetup("/dataset/profesores.xml")
    void toFindByIdTest(){
        int id =10;

        Optional<Profesor> profesor = profesorRepository.findById(id);

        assertAll( () -> {
            assertTrue(profesor.isPresent());
            assertEquals(id, profesor.get().getId());
            assertEquals("45357612", profesor.get().getDni());
            assertEquals("materia1", profesor.get().getMateria().getNombre());
        });

    }

    @Test
    @DatabaseSetup("/dataset/materias.xml")
    @DatabaseSetup("/dataset/profesores.xml")
    void toFindAllTest(){
        List<Profesor> profesors = (List<Profesor>) profesorRepository.findAll();
        assertAll( () -> {
            assertEquals(2, profesors.size());
            assertEquals(10, profesors.get(0).getId());
            assertEquals("45357612", profesors.get(0).getDni());
            assertEquals(20, profesors.get(1).getId());
            assertEquals("email2@gmail.com", profesors.get(1).getEmail());
        });
    }

    @Test
    @DatabaseSetup("/dataset/materias.xml")
    @DatabaseSetup("/dataset/profesores.xml")
    void deleteByIdAndExistByIdTest(){
        int id = 10;

        boolean existAntes = profesorRepository.existsById(id);
        profesorRepository.deleteById(id);
        boolean existDespues = profesorRepository.existsById(id);

        assertAll( () -> {
            assertTrue(existAntes);
            assertFalse(existDespues);
        });
    }

    @Test
    @DatabaseSetup("/dataset/materias.xml")
    @DatabaseSetup("/dataset/profesores.xml")
    void saveTest() {
        Profesor profesor = Profesor.builder()
                .nombre("AlumnoNombre")
                .apellido("AlumnoApellido")
                .sexo('O')
                .dni("87507431")
                .email("emailAlumno@hotmail.com")
                .celular("746536465")
                .telefono("65645646")
                .direccion("Direccion 123")
                .build();

        Profesor profesorSave = profesorRepository.save(profesor);
        boolean exits = profesorRepository.existsById(profesorSave.getId());

        assertAll(() -> {
            assertTrue(exits);
            assertNotNull(profesorSave);
            assertEquals(profesor.getDni(), profesorSave.getDni());
            assertEquals(profesor.getTelefono(), profesorSave.getTelefono());
        });
    }

    @Test
    @DatabaseSetup("/dataset/materias.xml")
    void getIdFromMateriaTest() {
        int id = profesorRepository.getIdFromMateria("materia2");

        assertEquals(20, id);
    }
}
