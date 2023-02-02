package com.juan.escuela.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "alumnos")
public class Alumno extends Persona {


    @Column(name = "numero_emergencia")
    private String numeroEmergencia;

    @Min(value = 1, message = "no puede ser menor a primer año")
    @Max(value = 5, message = "no hay mas que quinto año")
    @Column(name = "anio")
    private Short anio;

    @Min(value = 1, message = "no hay una división menos que la primera")
    @Max(value = 3, message = "solo hay hasta la tercera división")
    @Column(name = "division")
    private Short division;

    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY)
    private List<MateriaAlumno> materiaAlumnos;

    public Alumno() {
    }

    public Alumno(int id) {
        super(id);
    }

    public Alumno(int id, String numeroEmergencia, List<MateriaAlumno> materiaAlumnos) {
        super(id);
        this.numeroEmergencia = numeroEmergencia;
        this.materiaAlumnos = materiaAlumnos;
    }

    public Alumno(int id, String nombre, String apellido, String dni, String telefono, String celular, String email, LocalDate fechaNacimiento, char sexo, String direccion, String numeroEmergencia, Short anio, Short division, List<MateriaAlumno> materiaAlumnos) {
        super(id, nombre, apellido, dni, telefono, celular, email, fechaNacimiento, sexo, direccion);
        this.numeroEmergencia = numeroEmergencia;
        this.anio = anio;
        this.division = division;
        this.materiaAlumnos = materiaAlumnos;
    }

    public String getNumeroEmergencia() {
        return numeroEmergencia;
    }

    public void setNumeroEmergencia(String numeroEmergencia) {
        this.numeroEmergencia = numeroEmergencia;
    }

    public Short getAnio() {
        return anio;
    }

    public void setAnio(Short anio) {
        this.anio = anio;
    }

    public Short getDivision() {
        return division;
    }

    public void setDivision(Short division) {
        this.division = division;
    }

    public List<MateriaAlumno> getMateriaAlumnos() {
        return materiaAlumnos;
    }

    public void setMateriaAlumnos(List<MateriaAlumno> materiaAlumnos) {
        this.materiaAlumnos = materiaAlumnos;
    }

}
