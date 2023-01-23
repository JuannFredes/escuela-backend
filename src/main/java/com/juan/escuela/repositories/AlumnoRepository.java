package com.juan.escuela.repositories;

import com.juan.escuela.models.Alumno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AlumnoRepository extends CrudRepository<Alumno, Integer> {
}
