package com.juan.escuela.dto;

import java.time.LocalDate;

public class ProfesorDto extends PersonaDTO{

    private String materiaEncargado;

    public ProfesorDto() {
    }

    public ProfesorDto(int id) {
        super(id);
    }

    public ProfesorDto(int id, String nombre, String apellido, String dni, String telefono, String celular, int age, LocalDate fechaNacimiento, char sexo, String direccion, String email, String materiaEncargado) {
        super(id, nombre, apellido, dni, telefono, celular, age, fechaNacimiento, sexo, direccion, email);
        this.materiaEncargado = materiaEncargado;
    }

    public String getMateriaEncargado() {
        return materiaEncargado;
    }

    public void setMateriaEncargado(String materiaEncargado) {
        this.materiaEncargado = materiaEncargado;
    }

}

