package com.juan.escuela.repositories;

import com.juan.escuela.models.Profesor;
import org.springframework.data.repository.CrudRepository;

public interface ProfesorRepository extends CrudRepository<Profesor, Integer> {
}
