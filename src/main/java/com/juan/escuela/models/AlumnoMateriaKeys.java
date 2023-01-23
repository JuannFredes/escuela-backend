package com.juan.escuela.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AlumnoMateriaKeys implements Serializable {

    @Column(name = "id_materia")
    private int idMateria;
    @Column(name = "id_alumno")
    private int idAlumno;

    public AlumnoMateriaKeys() {
    }

    public AlumnoMateriaKeys(int idMateria, int idAlumno) {
        this.idMateria = idMateria;
        this.idAlumno = idAlumno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlumnoMateriaKeys that = (AlumnoMateriaKeys) o;
        return idMateria == that.idMateria && idAlumno == that.idAlumno;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMateria, idAlumno);
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

}
