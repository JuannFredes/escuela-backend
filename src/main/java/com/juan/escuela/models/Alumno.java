package com.juan.escuela.models;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "alumnos")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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

    public Alumno(int id) {
        super(id);
    }

    @Builder
    public Alumno(int id, @NotNull(message = "el nombre no puede ser nulo") @NotBlank(message = "el nombre no puede estar vacio") String nombre, @NotNull(message = "el apellido no puede ser nul") @NotBlank(message = "el apellido no puede estar vacio") String apellido, @Pattern(regexp = "^[0-9]{8}$", message = "el dni debe contener 8 numeros sin puntos") String dni, String telefono, String celular, @Email(message = "formato de e-mail no valido") String email, LocalDate fechaNacimiento, char sexo, String direccion, LocalDateTime create, String numeroEmergencia, Short anio, Short division, List<MateriaAlumno> materiaAlumnos) {
        super(id, nombre, apellido, dni, telefono, celular, email, fechaNacimiento, sexo, direccion, create);
        this.numeroEmergencia = numeroEmergencia;
        this.anio = anio;
        this.division = division;
        this.materiaAlumnos = materiaAlumnos;
    }
}
