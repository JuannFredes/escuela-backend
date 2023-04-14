package com.juan.escuela.repositories;

import com.juan.escuela.dto.ProfesorDto;
import com.juan.escuela.exceptions.AppException;
import com.juan.escuela.models.Profesor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public interface ProfesorRepository extends CrudRepository<Profesor, Integer> {
    @Query(value = "SELECT m.id FROM materias m WHERE m.nombre = :nombre", nativeQuery = true)
    Optional<Integer> findIdByNombre(String nombre);

    default Integer getIdFromMateria(String nombre) {
        Integer id = findIdByNombre(nombre).orElseThrow(() -> new AppException("la materia que se encarga el profesor no existe, cree la materia primero", HttpStatus.BAD_REQUEST));
        return id;
    }
}
