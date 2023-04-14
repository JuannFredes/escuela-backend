package com.juan.escuela.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MateriaDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int id;
    private String nombre;

}
