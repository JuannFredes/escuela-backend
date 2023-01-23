package com.juan.escuela.repositories;

import com.juan.escuela.models.AlumnoMateriaKeys;
import com.juan.escuela.models.MateriaAlumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaAlumnoRepository extends JpaRepository<MateriaAlumno, AlumnoMateriaKeys> {
}
