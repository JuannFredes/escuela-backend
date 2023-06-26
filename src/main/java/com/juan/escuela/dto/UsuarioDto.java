package com.juan.escuela.dto;

import com.juan.escuela.models.ERol;
import com.juan.escuela.models.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UsuarioDto {

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String password;
    private Set<String> roles;

}
