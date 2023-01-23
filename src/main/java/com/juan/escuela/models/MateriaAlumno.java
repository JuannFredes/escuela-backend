package com.juan.escuela.models;

import javax.persistence.*;

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

    public MateriaAlumno() {
    }

    public MateriaAlumno(AlumnoMateriaKeys alumnoMateriaKeys) {
        this.alumnoMateriaKeys = alumnoMateriaKeys;
    }

    public MateriaAlumno(AlumnoMateriaKeys alumnoMateriaKeys/*, Alumno alumno, Materia materia*/, short nota) {
        this.alumnoMateriaKeys = alumnoMateriaKeys;
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }

    public AlumnoMateriaKeys getAlumnoMateriaKeys() {
        return alumnoMateriaKeys;
    }

    public void setAlumnoMateriaKeys(AlumnoMateriaKeys alumnoMateriaKeys) {
        this.alumnoMateriaKeys = alumnoMateriaKeys;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public short getNota() {
        return nota;
    }

    public void setNota(short nota) {
        this.nota = nota;
    }

}
