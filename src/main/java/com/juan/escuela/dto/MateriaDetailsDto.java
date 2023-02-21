package com.juan.escuela.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MateriaDetailsDto extends MateriaDto {
    private List<ProfesorDto> profesors;
    @JsonProperty("cantidad_alumnos")
    private int cantidadAlumnos;
    private List<AlumnoDto> alumnos;

    @Builder(builderMethodName = "MateriaDetailsBuilder")
    public MateriaDetailsDto(int id, String nombre, List<ProfesorDto> profesors, int cantidadAlumnos, List<AlumnoDto> alumnos) {
        super(id, nombre);
        this.profesors = profesors;
        this.cantidadAlumnos = cantidadAlumnos;
        this.alumnos = alumnos;
    }
}
