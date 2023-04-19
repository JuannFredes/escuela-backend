package com.juan.escuela.mappers;

import com.juan.escuela.dto.AlumnoDto;
import com.juan.escuela.dto.AlumnoMateriasDto;
import com.juan.escuela.dto.MateriaNotaDto;
import com.juan.escuela.models.Alumno;
import com.juan.escuela.models.Materia;
import com.juan.escuela.models.MateriaAlumno;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-19T11:59:30-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Ubuntu)"
)
@Component
public class AlumnoMapperImpl implements AlumnoMapper {

    @Override
    public AlumnoDto toAlumnoDto(Alumno alumno) {
        if ( alumno == null ) {
            return null;
        }

        AlumnoDto.AlumnoDtoBuilder alumnoDto = AlumnoDto.builder();

        alumnoDto.id( alumno.getId() );
        alumnoDto.nombre( alumno.getNombre() );
        alumnoDto.apellido( alumno.getApellido() );
        alumnoDto.dni( alumno.getDni() );
        alumnoDto.telefono( alumno.getTelefono() );
        alumnoDto.celular( alumno.getCelular() );
        alumnoDto.email( alumno.getEmail() );
        alumnoDto.fechaNacimiento( alumno.getFechaNacimiento() );
        alumnoDto.sexo( alumno.getSexo() );
        alumnoDto.direccion( alumno.getDireccion() );
        alumnoDto.numeroEmergencia( alumno.getNumeroEmergencia() );
        alumnoDto.anio( alumno.getAnio() );
        alumnoDto.division( alumno.getDivision() );

        alumnoDto.age( java.time.Period.between(alumno.getFechaNacimiento(), java.time.LocalDate.now()).getYears() );

        return alumnoDto.build();
    }

    @Override
    public Alumno toAlumno(AlumnoDto alumnoDto) {
        if ( alumnoDto == null ) {
            return null;
        }

        Alumno.AlumnoBuilder alumno = Alumno.builder();

        alumno.id( alumnoDto.getId() );
        alumno.nombre( alumnoDto.getNombre() );
        alumno.apellido( alumnoDto.getApellido() );
        alumno.dni( alumnoDto.getDni() );
        alumno.telefono( alumnoDto.getTelefono() );
        alumno.celular( alumnoDto.getCelular() );
        alumno.email( alumnoDto.getEmail() );
        alumno.fechaNacimiento( alumnoDto.getFechaNacimiento() );
        alumno.sexo( alumnoDto.getSexo() );
        alumno.direccion( alumnoDto.getDireccion() );
        alumno.numeroEmergencia( alumnoDto.getNumeroEmergencia() );
        alumno.anio( alumnoDto.getAnio() );
        alumno.division( alumnoDto.getDivision() );

        return alumno.build();
    }

    @Override
    public List<AlumnoDto> toListAlumnoDto(List<Alumno> alumnos) {
        if ( alumnos == null ) {
            return null;
        }

        List<AlumnoDto> list = new ArrayList<AlumnoDto>( alumnos.size() );
        for ( Alumno alumno : alumnos ) {
            list.add( toAlumnoDto( alumno ) );
        }

        return list;
    }

    @Override
    public MateriaNotaDto toMateriaAlumnoDto(MateriaAlumno materiaAlumno) {
        if ( materiaAlumno == null ) {
            return null;
        }

        MateriaNotaDto.MateriaNotaDtoBuilder materiaNotaDto = MateriaNotaDto.MateriaNotaBuilder();

        materiaNotaDto.nombre( materiaAlumnoMateriaNombre( materiaAlumno ) );
        materiaNotaDto.id( materiaAlumnoMateriaId( materiaAlumno ) );
        materiaNotaDto.nota( materiaAlumno.getNota() );

        return materiaNotaDto.build();
    }

    @Override
    public List<MateriaNotaDto> toListMateriaAlumntoDto(List<MateriaAlumno> materiaAlumnos) {
        if ( materiaAlumnos == null ) {
            return null;
        }

        List<MateriaNotaDto> list = new ArrayList<MateriaNotaDto>( materiaAlumnos.size() );
        for ( MateriaAlumno materiaAlumno : materiaAlumnos ) {
            list.add( toMateriaAlumnoDto( materiaAlumno ) );
        }

        return list;
    }

    @Override
    public AlumnoMateriasDto toAlumnoMateriasDto(Alumno alumno) {
        if ( alumno == null ) {
            return null;
        }

        AlumnoMateriasDto.AlumnoMateriasDtoBuilder alumnoMateriasDto = AlumnoMateriasDto.AlumnosMateriaBuilder();

        alumnoMateriasDto.materias( toListMateriaAlumntoDto( alumno.getMateriaAlumnos() ) );
        alumnoMateriasDto.fechaNacimiento( alumno.getFechaNacimiento() );
        alumnoMateriasDto.id( alumno.getId() );
        alumnoMateriasDto.nombre( alumno.getNombre() );
        alumnoMateriasDto.apellido( alumno.getApellido() );
        alumnoMateriasDto.dni( alumno.getDni() );
        alumnoMateriasDto.telefono( alumno.getTelefono() );
        alumnoMateriasDto.celular( alumno.getCelular() );
        alumnoMateriasDto.email( alumno.getEmail() );
        alumnoMateriasDto.sexo( alumno.getSexo() );
        alumnoMateriasDto.direccion( alumno.getDireccion() );
        alumnoMateriasDto.numeroEmergencia( alumno.getNumeroEmergencia() );
        alumnoMateriasDto.anio( alumno.getAnio() );
        alumnoMateriasDto.division( alumno.getDivision() );

        alumnoMateriasDto.age( java.time.Period.between(alumno.getFechaNacimiento(), java.time.LocalDate.now()).getYears() );

        return alumnoMateriasDto.build();
    }

    private String materiaAlumnoMateriaNombre(MateriaAlumno materiaAlumno) {
        if ( materiaAlumno == null ) {
            return null;
        }
        Materia materia = materiaAlumno.getMateria();
        if ( materia == null ) {
            return null;
        }
        String nombre = materia.getNombre();
        if ( nombre == null ) {
            return null;
        }
        return nombre;
    }

    private int materiaAlumnoMateriaId(MateriaAlumno materiaAlumno) {
        if ( materiaAlumno == null ) {
            return 0;
        }
        Materia materia = materiaAlumno.getMateria();
        if ( materia == null ) {
            return 0;
        }
        int id = materia.getId();
        return id;
    }
}
