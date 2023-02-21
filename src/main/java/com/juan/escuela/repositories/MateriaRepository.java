package com.juan.escuela.repositories;

import com.juan.escuela.models.Materia;
import org.springframework.data.repository.CrudRepository;

public interface MateriaRepository extends CrudRepository<Materia, Integer> {
}
