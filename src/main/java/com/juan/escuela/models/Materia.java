package com.juan.escuela.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "materias")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "el nombre de la materia no puede ser nulo")
    @NotBlank(message = "el nombre de la materia no puede estar vacio")
    private String nombre;

    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL)
    private List<Profesor> profesors;

    @OneToMany(mappedBy = "materia")
    private List<MateriaAlumno> materiaAlumnos;

    public Materia() {
    }

    public Materia(int id) {
        this.id = id;
    }

    public Materia(int id, String nombre, List<Profesor> profesors, List<MateriaAlumno> materiaAlumnos) {
        this.id = id;
        this.nombre = nombre;
        this.profesors = profesors;
        this.materiaAlumnos = materiaAlumnos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Profesor> getProfesors() {
        return profesors;
    }

    public void setProfesors(List<Profesor> profesors) {
        this.profesors = profesors;
    }

    public List<MateriaAlumno> getMateriaAlumnos() {
        return materiaAlumnos;
    }

    public void setMateriaAlumnos(List<MateriaAlumno> materiaAlumnos) {
        this.materiaAlumnos = materiaAlumnos;
    }

}
