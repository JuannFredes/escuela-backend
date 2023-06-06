package com.juan.escuela.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AlumnoDto extends PersonaDTO{

    @JsonProperty("numero_emergencia")
    private String numeroEmergencia;
    @Min(value = 1, message = "no puede ser menor a primer año")
    @Max(value = 5, message = "no hay mas que quinto año")
    private Short anio;
    @Min(value = 1, message = "no hay una división menos que la primera")
    @Max(value = 3, message = "solo hay hasta la tercera división")
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
