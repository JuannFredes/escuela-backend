package com.juan.escuela.mappers;

import com.juan.escuela.dto.AlumnoDto;
import com.juan.escuela.dto.AlumnoMateriasDto;
import com.juan.escuela.dto.MateriaNotaDto;
import com.juan.escuela.models.Alumno;
import com.juan.escuela.models.MateriaAlumno;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlumnoMapper {

    @Named(value = "AlumnoDto")
    @Mapping(target = "age", expression = "java(java.time.Period.between(alumno.getFechaNacimiento(), java.time.LocalDate.now()).getYears())")
    AlumnoDto toAlumnoDto(Alumno alumno);

    Alumno toAlumno(AlumnoDto alumnoDto);

    @IterableMapping(qualifiedByName = "AlumnoDto")
    List<AlumnoDto> toListAlumnoDto(List<Alumno> alumnos);

    @Mappings({
            @Mapping(target = "nombre", source = "materia.nombre"),
            @Mapping(target = "id", source = "materia.id")
    })
    MateriaNotaDto toMateriaAlumnoDto(MateriaAlumno materiaAlumno);


    List<MateriaNotaDto> toListMateriaAlumntoDto(List<MateriaAlumno> materiaAlumnos);

    @Mappings({
            @Mapping(target = "materias", source= "materiaAlumnos"),
            @Mapping(target = "age", expression = "java(java.time.Period.between(alumno.getFechaNacimiento(), java.time.LocalDate.now()).getYears())"),
            @Mapping(target = "fechaNacimiento", source = "fechaNacimiento", dateFormat = "dd-MM-yyyy")
    })
    AlumnoMateriasDto toAlumnoMateriasDto(Alumno alumno);

}
