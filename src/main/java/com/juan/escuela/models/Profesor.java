package com.juan.escuela.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "profesores")
public class Profesor extends Persona{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_materia")
    private Materia materia;

    public Profesor() {
    }

    public Profesor(int id) {
        super(id);
    }

    public Profesor(int id, String nombre, String apellido, String dni, String telefono, String celular, String email, LocalDate fechaNacimiento, char sexo, String direccion, Materia materia) {
        super(id, nombre, apellido, dni, telefono, celular, email, fechaNacimiento, sexo, direccion);
        this.materia = materia;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

}
