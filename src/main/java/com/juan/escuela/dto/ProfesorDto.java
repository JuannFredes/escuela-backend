package com.juan.escuela.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProfesorDto extends PersonaDTO{

    @JsonProperty("materia_encargado")
    private String materiaEncargado;


    public ProfesorDto(int id) {
        super(id);
    }

    @Builder
    public ProfesorDto(int id, String nombre, String apellido, String dni, String telefono, String celular, String email, int age, LocalDate fechaNacimiento, char sexo, String direccion, String materiaEncargado) {
        super(id, nombre, apellido, dni, telefono, celular, email, age, fechaNacimiento, sexo, direccion);
        this.materiaEncargado = materiaEncargado;
    }

    /*
    public String getMateriaEncargado() {
        return materiaEncargado;
    }

    public void setMateriaEncargado(String materiaEncargado) {
        this.materiaEncargado = materiaEncargado;
    }*/

}

