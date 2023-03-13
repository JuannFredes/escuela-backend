package com.juan.escuela.repositories;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.juan.escuela.models.Materia;
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
public class MateriaRepositoryTest {

    @Autowired
    private MateriaRepository materiaRepository;

    @Test
    @DatabaseSetup("/dataset/alumnos.xml")
    @DatabaseSetup("/dataset/materias.xml")
    @DatabaseSetup("/dataset/MATERIAS_ALUMNOS.xml")
    @DatabaseSetup("/dataset/profesores.xml")
    void toFindByIdTest(){
        int id =10;

        Optional<Materia> materia = materiaRepository.findById(id);

        assertAll( () -> {
            assertTrue(materia.isPresent());
            assertEquals(id, materia.get().getId());
            assertEquals("materia1", materia.get().getNombre());
            assertEquals("67545989", materia.get().getMateriaAlumnos().get(0).getAlumno().getDni());
            assertEquals(4, materia.get().getMateriaAlumnos().get(0).getNota());
            assertEquals("45357612", materia.get().getProfesors().get(0).getDni());
        });

    }

    @Test
    @DatabaseSetup("/dataset/materias.xml")
    void toFindAllTest(){
        List<Materia> materias = (List<Materia>) materiaRepository.findAll();
        assertAll( () -> {
            assertEquals(2, materias.size());
            assertEquals(10, materias.get(0).getId());
            assertEquals("materia1", materias.get(0).getNombre());
            assertEquals(20, materias.get(1).getId());
            assertEquals("materia2", materias.get(1).getNombre());
        });
    }

    @Test
    @DatabaseSetup("/dataset/materias.xml")
    void deleteByIdAndExistByIdTest(){
        int id = 10;

        boolean existAntes = materiaRepository.existsById(id);
        materiaRepository.deleteById(id);
        boolean existDespues = materiaRepository.existsById(id);

        assertAll( () -> {
            assertTrue(existAntes);
            assertFalse(existDespues);
        });
    }

    @Test
    @DatabaseSetup("/dataset/materias.xml")
    void saveTest(){
        Materia materia = Materia.builder()
                .nombre("MateriaTest")
                .build();

        Materia materiaSave = materiaRepository.save(materia);
        boolean exits = materiaRepository.existsById(materiaSave.getId());

        assertAll( () -> {
            assertTrue(exits);
            assertNotNull(materiaSave);
            assertEquals(materia.getNombre(), materiaSave.getNombre());
        });

    }
}
