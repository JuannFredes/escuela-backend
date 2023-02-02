package com.juan.escuela.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MateriaDetailsDto extends MateriaDto {
    private List<ProfesorDto> profesors;
    @JsonProperty("cantidad_alumnos")
    private int cantidadAlumnos;
    private List<AlumnoDto> alumnos;

    public MateriaDetailsDto() {
    }

    public MateriaDetailsDto(int id, String nombre, List<ProfesorDto> profesors, int cantidadAlumnos, List<AlumnoDto> alumnos) {
        super(id, nombre);
        this.profesors = profesors;
        this.cantidadAlumnos = cantidadAlumnos;
        this.alumnos = alumnos;
    }

    public List<ProfesorDto> getProfesors() {
        return profesors;
    }

    public void setProfesors(List<ProfesorDto> profesors) {
        this.profesors = profesors;
    }

    public int getCantidadAlumnos() {
        return cantidadAlumnos;
    }

    public void setCantidadAlumnos(int cantidadAlumnos) {
        this.cantidadAlumnos = cantidadAlumnos;
    }

    public List<AlumnoDto> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<AlumnoDto> alumnos) {
        this.alumnos = alumnos;
    }
}
