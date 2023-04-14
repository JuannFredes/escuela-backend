package com.juan.escuela.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    public Materia(int id) {
        this.id = id;
    }

    public Materia(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
