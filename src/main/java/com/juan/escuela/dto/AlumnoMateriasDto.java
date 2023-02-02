package com.juan.escuela.dto;

import java.time.LocalDate;
import java.util.List;

public class AlumnoMateriasDto extends AlumnoDto {

    private List<MateriaNotaDto> materias;

    public AlumnoMateriasDto() {
    }


    public AlumnoMateriasDto(int id, String nombre, String apellido, String dni, String telefono, String celular, int age, LocalDate fechaNacimiento, char sexo, String direccion, String email, String numeroEmergencia, Short anio, Short division, List<MateriaNotaDto> materias) {
        super(id, nombre, apellido, dni, telefono, celular, age, fechaNacimiento, sexo, direccion, email, numeroEmergencia, anio, division);
        this.materias = materias;
    }

    public List<MateriaNotaDto> getMaterias() {
        return materias;
    }

    public void setMaterias(List<MateriaNotaDto> materias) {
        this.materias = materias;
    }
}

