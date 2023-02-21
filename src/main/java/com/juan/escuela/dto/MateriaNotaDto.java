package com.juan.escuela.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor

public class MateriaNotaDto extends MateriaDto {

    @Min(value = 0, message = "la nota no puede ser un numero negativo")
    @Max(value = 10, message = "la nota no puede ser mas de 10")
    private short nota;

    @Builder(builderMethodName = "MateriaNotaBuilder")
    public MateriaNotaDto(int id, String nombre, short nota) {
        super(id, nombre);
        this.nota = nota;
    }
}
