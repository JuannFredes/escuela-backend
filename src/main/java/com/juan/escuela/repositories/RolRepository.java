package com.juan.escuela.repositories;

import com.juan.escuela.models.Rol;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RolRepository extends CrudRepository<Rol, Integer> {

    @Query(value = "SELECT * FROM rols  WHERE name = ?1", nativeQuery = true)
    Optional<Rol> getRol(String name);
}
