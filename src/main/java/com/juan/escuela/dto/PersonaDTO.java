package com.juan.escuela.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class PersonaDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String celular;
    private String email;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int age;
    @JsonProperty("fecha_nacimiento")
    private LocalDate fechaNacimiento;
    private char sexo;
    private String direccion;

    public PersonaDTO(int id) {
        this.id = id;
    }

}
