package com.juan.escuela.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AlumnoMateriasDto extends AlumnoDto {

    private List<MateriaNotaDto> materias;

    @Builder(builderMethodName = "AlumnosMateriaBuilder")
    public AlumnoMateriasDto(int id, String nombre, String apellido, String dni, String telefono, String celular, String email, int age, LocalDate fechaNacimiento, char sexo, String direccion, String numeroEmergencia, Short anio, Short division, List<MateriaNotaDto> materias) {
        super(id, nombre, apellido, dni, telefono, celular, email, age, fechaNacimiento, sexo, direccion, numeroEmergencia, anio, division);
        this.materias = materias;
    }

}

