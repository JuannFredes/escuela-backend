package com.juan.escuela.mappers;

import com.juan.escuela.dto.RegistroDto;
import com.juan.escuela.dto.UsuarioDto;
import com.juan.escuela.models.ERol;
import com.juan.escuela.models.Rol;
import com.juan.escuela.models.Usuario;
import org.mapstruct.*;

import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    @Mapping(target = "roles", qualifiedByName = "mapToSetRol")
    Usuario toUsuario(UsuarioDto usuarioDto);

    @Named("toUsuarioDto")
    @Mapping(target = "roles", qualifiedByName = "mapToSetString")
    @Mapping(target = "token", constant = "*****")
    UsuarioDto toUsuarioDto(Usuario usuario);

    @IterableMapping(qualifiedByName = "toUsuarioDto")
    List<UsuarioDto> toListUsuarioDto(List<Usuario> usuarios);

    Usuario toUsuario(RegistroDto registroDto);

    @Named("mapToSetRol")
    default Set<Rol> mapRolesToRol(Set<String> roles) {
        return roles.stream()
                .map(rol -> Rol.builder().name(ERol.valueOf(rol)).build())
                .collect(Collectors.toSet());
    }

    @Named("mapToSetString")
    default Set<String> mapRolesToString(Set<Rol> roles) {
        return roles.stream()
                .map(rol -> rol.getName().name())
                .collect(Collectors.toSet());
    }
}
