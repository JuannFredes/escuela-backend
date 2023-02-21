package com.juan.escuela.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class AlumnoMateriaKeys implements Serializable {

    @Column(name = "id_materia")
    private int idMateria;
    @Column(name = "id_alumno")
    private int idAlumno;

}
