package com.juan.escuela.mappers;

import com.juan.escuela.dto.ProfesorDto;
import com.juan.escuela.models.Profesor;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfesorMapper {

    @Mappings(
            {
                    @Mapping(target = "materiaEncargado", source = "materia.nombre"),
                    @Mapping(target = "age", expression = "java(java.time.Period.between(profesor.getFechaNacimiento(), java.time.LocalDate.now()).getYears())")
            }
    )
    ProfesorDto toProfesorDto(Profesor profesor);

    List<ProfesorDto> toListProfesorDto(List<Profesor> profesors);
}
