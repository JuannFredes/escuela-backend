package com.juan.escuela.models;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "profesores")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Profesor extends Persona{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_materia")
    private Materia materia;

    public Profesor(int id) {
        super(id);
    }

    @Builder
    public Profesor(int id, @NotNull(message = "el nombre no puede ser nulo") @NotBlank(message = "el nombre no puede estar vacio") String nombre, @NotNull(message = "el apellido no puede ser nul") @NotBlank(message = "el apellido no puede estar vacio") String apellido, @Pattern(regexp = "^[0-9]{8}$", message = "el dni debe contener 8 numeros sin puntos") String dni, String telefono, String celular, @Email(message = "formato de e-mail no valido") String email, LocalDate fechaNacimiento, char sexo, String direccion, LocalDateTime create, Materia materia) {
        super(id, nombre, apellido, dni, telefono, celular, email, fechaNacimiento, sexo, direccion, create);
        this.materia = materia;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

}
