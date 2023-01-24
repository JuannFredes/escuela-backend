package com.juan.escuela.dto;

import java.time.LocalDate;

public class AlumnoDto extends PersonaDTO{

    private String numeroReferencia;

    public AlumnoDto() {
    }

    public AlumnoDto(int id) {
        super(id);
    }

    public AlumnoDto(int id, String nombre, String apellido, String dni, String telefono, String celular, int age, LocalDate fechaNacimiento, char sexo, String direccion, String numeroReferencia, String email) {
        super(id, nombre, apellido, dni, telefono, celular, age, fechaNacimiento, sexo, direccion, email);
        this.numeroReferencia = numeroReferencia;
    }

    public String getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferencia(String numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }
}
