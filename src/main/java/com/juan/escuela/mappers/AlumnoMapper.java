package com.juan.escuela.mappers;

import com.juan.escuela.dto.AlumnoDto;
import com.juan.escuela.models.Alumno;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlumnoMapper {

    @Mapping(target = "age", expression = "java(java.time.Period.between(alumno.getFechaNacimiento(), java.time.LocalDate.now()).getYears())")
    AlumnoDto toAlumnoDto(Alumno alumno);

    List<AlumnoDto> toListAlumnoDto(List<Alumno> alumnos);

}
