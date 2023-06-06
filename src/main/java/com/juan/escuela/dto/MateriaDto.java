package com.juan.escuela.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MateriaDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int id;
    @NotNull(message = "el nombre de la materia no puede ser nulo")
    @NotBlank(message = "el nombre de la materia no puede estar vacio")
    private String nombre;

}
