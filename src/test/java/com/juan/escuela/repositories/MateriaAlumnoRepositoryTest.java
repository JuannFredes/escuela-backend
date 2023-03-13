package com.juan.escuela.repositories;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.juan.escuela.models.Alumno;
import com.juan.escuela.models.AlumnoMateriaKeys;
import com.juan.escuela.models.Materia;
import com.juan.escuela.models.MateriaAlumno;
import com.juan.escuela.services.MateriaAlumnoService;
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
public class MateriaAlumnoRepositoryTest {

    @Autowired
    private MateriaAlumnoRepository materiaAlumnoRepository;

    @Test
    @DatabaseSetup("/dataset/alumnos.xml")
    @DatabaseSetup("/dataset/materias.xml")
    @DatabaseSetup("/dataset/MATERIAS_ALUMNOS.xml")
    void saveTest(){
        MateriaAlumno materiaAlumno = MateriaAlumno.builder()
                .alumnoMateriaKeys(new AlumnoMateriaKeys(10, 20))
                .alumno(new Alumno(20))
                .materia(new Materia(10))
                .nota((short) 10)
                .build();

        MateriaAlumno materiaAlumnoSave = materiaAlumnoRepository.save(materiaAlumno);
        boolean exits = materiaAlumnoRepository.existsById(materiaAlumnoSave.getAlumnoMateriaKeys());

        assertAll( () -> {
            assertTrue(exits);
            assertNotNull(materiaAlumnoSave);
            assertEquals(materiaAlumno.getNota(), materiaAlumnoSave.getNota());
        });

    }
}
