package com.juan.escuela.dto;

public class MateriaDto {
    private int id;
    private String nombre;

    public MateriaDto() {
    }

    public MateriaDto(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
