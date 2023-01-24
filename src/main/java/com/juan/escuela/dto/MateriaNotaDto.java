package com.juan.escuela.dto;

public class MateriaNotaDto extends MateriaDto {
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
