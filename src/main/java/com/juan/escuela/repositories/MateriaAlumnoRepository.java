package com.juan.escuela.repositories;

import com.juan.escuela.exceptions.AppException;
import com.juan.escuela.models.AlumnoMateriaKeys;
import com.juan.escuela.models.MateriaAlumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public interface MateriaAlumnoRepository extends CrudRepository<MateriaAlumno, AlumnoMateriaKeys> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM materias m WHERE m.id = :idMateria) AND EXISTS (SELECT 1 FROM alumnos a WHERE a.id = :idAlumno) AS existen", nativeQuery = true)
    short alumnoMateriaExistsById(@Param("idMateria") int idMateria, @Param("idAlumno") int idAlumno);
}
