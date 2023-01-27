package com.juan.escuela.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class MateriaNotaDto extends MateriaDto {

    @Min(value = 0, message = "la nota no puede ser un numero negativo")
    @Max(value = 10, message = "la nota no puede ser mas de 10")
    private short nota;

    public MateriaNotaDto() {
    }

    public MateriaNotaDto(int id, String nombre, short nota) {
        super(id, nombre);
        this.nota = nota;
    }

    public short getNota() {
        return nota;
    }

    public void setNota(short nota) {
        this.nota = nota;
    }
}
