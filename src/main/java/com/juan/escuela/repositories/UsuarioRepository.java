package com.juan.escuela.repositories;

import com.juan.escuela.models.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    @Query(value = "SELECT * FROM usuarios WHERE user = :idUser", nativeQuery = true)
    Optional<Usuario> getUser(@Param("idUser") String user);
}
