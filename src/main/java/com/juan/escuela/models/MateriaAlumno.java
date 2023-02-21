package com.juan.escuela.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "MATERIAS_ALUMNOS")
public class MateriaAlumno {

    @EmbeddedId
    private AlumnoMateriaKeys alumnoMateriaKeys;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idMateria")
    @JoinColumn(name = "id_materia")
    private Materia materia;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idAlumno")
    @JoinColumn(name = "id_alumno")
    private Alumno alumno;
    private short nota;

    public MateriaAlumno(AlumnoMateriaKeys alumnoMateriaKeys) {
        this.alumnoMateriaKeys = alumnoMateriaKeys;
    }


}
