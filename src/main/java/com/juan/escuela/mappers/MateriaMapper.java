package com.juan.escuela.mappers;

import com.juan.escuela.dto.MateriaDetailsDto;
import com.juan.escuela.dto.MateriaDto;
import com.juan.escuela.dto.MateriaNotaDto;
import com.juan.escuela.models.Alumno;
import com.juan.escuela.models.Materia;
import com.juan.escuela.models.MateriaAlumno;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AlumnoMapper.class, ProfesorMapper.class})
public interface MateriaMapper {

    @Named(value = "toMateriaDto")
    MateriaDto toMateriaDto(Materia materia);
    Materia toMateria(MateriaDto materiaDto);
    @IterableMapping(qualifiedByName = "toMateriaDto")
    List<MateriaDto> toListMateriaDto(List<Materia> materias);

    //@Named("toAlumno")
    @Mapping(target = ".", source = "alumno")
    Alumno toAlumno(MateriaAlumno materiaAlumno);

    //@IterableMapping(qualifiedByName = "toAlumno")
    List<Alumno> toListAlumno(List<MateriaAlumno> materiaAlumnos);

    @Mappings({
            @Mapping(target = "alumnos", source = "materiaAlumnos", qualifiedByName = "AlumnoDto"),
            @Mapping(target = "cantidadAlumnos", expression = "java(materia.getMateriaAlumnos().size())")
    })
    MateriaDetailsDto toMateriaDetailsDto(Materia materia);

    @Mappings({
            @Mapping(target = "id", source = "materia.id"),
            @Mapping(target = "nombre", source = "materia.nombre")
    })
    MateriaNotaDto toMateriaNotaDto(MateriaAlumno materiaAlumno);

}
