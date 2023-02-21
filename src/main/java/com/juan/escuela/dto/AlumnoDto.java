package com.juan.escuela.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AlumnoDto extends PersonaDTO{

    @JsonProperty("numero_emergencia")
    private String numeroEmergencia;
    private Short anio;
    private Short division;
    public AlumnoDto(int id) {
        super(id);
    }

    @Builder
    public AlumnoDto(int id, String nombre, String apellido, String dni, String telefono, String celular, String email, int age, LocalDate fechaNacimiento, char sexo, String direccion, String numeroEmergencia, Short anio, Short division) {
        super(id, nombre, apellido, dni, telefono, celular, email, age, fechaNacimiento, sexo, direccion);
        this.numeroEmergencia = numeroEmergencia;
        this.anio = anio;
        this.division = division;
    }

}
