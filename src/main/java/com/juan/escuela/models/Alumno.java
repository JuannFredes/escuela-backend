package com.juan.escuela.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "alumnos")
public class Alumno extends Persona {


    @Column(name = "numero_referencia")
    private String numeroReferencia;

    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY)
    private List<MateriaAlumno> materiaAlumnos;

    public Alumno() {
    }

    public Alumno(int id) {
        super(id);
    }

    public Alumno(int id, String numeroReferencia, List<MateriaAlumno> materiaAlumnos) {
        super(id);
        this.numeroReferencia = numeroReferencia;
        this.materiaAlumnos = materiaAlumnos;
    }

    public Alumno(int id, String nombre, String apellido, String dni, String telefono, String celular, String email, LocalDate fechaNacimiento, char sexo, String direccion, String numeroReferencia, List<MateriaAlumno> materiaAlumnos) {
        super(id, nombre, apellido, dni, telefono, celular, email, fechaNacimiento, sexo, direccion);
        this.numeroReferencia = numeroReferencia;
        this.materiaAlumnos = materiaAlumnos;
    }

    public String getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferencia(String numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public List<MateriaAlumno> getMateriaAlumnos() {
        return materiaAlumnos;
    }

    public void setMateriaAlumnos(List<MateriaAlumno> materiaAlumnos) {
        this.materiaAlumnos = materiaAlumnos;
    }

}
