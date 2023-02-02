package com.juan.escuela.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class AlumnoDto extends PersonaDTO{

    @JsonProperty("numero_emergencia")
    private String numeroEmergencia;

    private Short anio;
    private Short division;


    public AlumnoDto() {
    }

    public AlumnoDto(int id) {
        super(id);
    }

    public AlumnoDto(int id, String nombre, String apellido, String dni, String telefono, String celular, int age, LocalDate fechaNacimiento, char sexo, String direccion, String email, String numeroEmergencia, Short anio, Short division) {
        super(id, nombre, apellido, dni, telefono, celular, age, fechaNacimiento, sexo, direccion, email);
        this.numeroEmergencia = numeroEmergencia;
        this.anio = anio;
        this.division = division;
    }
    public String getNumeroEmergencia() {
        return numeroEmergencia;
    }

    public void setNumeroEmergencia(String numeroEmergencia) {
        this.numeroEmergencia = numeroEmergencia;
    }

    public Short getAnio() {
        return anio;
    }

    public void setAnio(Short anio) {
        this.anio = anio;
    }

    public Short getDivision() {
        return division;
    }

    public void setDivision(Short division) {
        this.division = division;
    }
}
