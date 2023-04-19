package com.juan.escuela.mappers;

import com.juan.escuela.dto.ProfesorDto;
import com.juan.escuela.models.Materia;
import com.juan.escuela.models.Profesor;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-19T11:59:31-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Ubuntu)"
)
@Component
public class ProfesorMapperImpl implements ProfesorMapper {

    @Override
    public ProfesorDto toProfesorDto(Profesor profesor) {
        if ( profesor == null ) {
            return null;
        }

        ProfesorDto.ProfesorDtoBuilder profesorDto = ProfesorDto.builder();

        profesorDto.materiaEncargado( profesorMateriaNombre( profesor ) );
        profesorDto.id( profesor.getId() );
        profesorDto.nombre( profesor.getNombre() );
        profesorDto.apellido( profesor.getApellido() );
        profesorDto.dni( profesor.getDni() );
        profesorDto.telefono( profesor.getTelefono() );
        profesorDto.celular( profesor.getCelular() );
        profesorDto.email( profesor.getEmail() );
        profesorDto.fechaNacimiento( profesor.getFechaNacimiento() );
        profesorDto.sexo( profesor.getSexo() );
        profesorDto.direccion( profesor.getDireccion() );

        profesorDto.age( java.time.Period.between(profesor.getFechaNacimiento(), java.time.LocalDate.now()).getYears() );

        return profesorDto.build();
    }

    @Override
    public Profesor toProfesor(ProfesorDto profesorDto) {
        if ( profesorDto == null ) {
            return null;
        }

        Profesor.ProfesorBuilder profesor = Profesor.builder();

        profesor.materia( profesorDtoToMateria( profesorDto ) );
        profesor.id( profesorDto.getId() );
        profesor.nombre( profesorDto.getNombre() );
        profesor.apellido( profesorDto.getApellido() );
        profesor.dni( profesorDto.getDni() );
        profesor.telefono( profesorDto.getTelefono() );
        profesor.celular( profesorDto.getCelular() );
        profesor.email( profesorDto.getEmail() );
        profesor.fechaNacimiento( profesorDto.getFechaNacimiento() );
        profesor.sexo( profesorDto.getSexo() );
        profesor.direccion( profesorDto.getDireccion() );

        return profesor.build();
    }

    @Override
    public List<ProfesorDto> toListProfesorDto(List<Profesor> profesors) {
        if ( profesors == null ) {
            return null;
        }

        List<ProfesorDto> list = new ArrayList<ProfesorDto>( profesors.size() );
        for ( Profesor profesor : profesors ) {
            list.add( toProfesorDto( profesor ) );
        }

        return list;
    }

    private String profesorMateriaNombre(Profesor profesor) {
        if ( profesor == null ) {
            return null;
        }
        Materia materia = profesor.getMateria();
        if ( materia == null ) {
            return null;
        }
        String nombre = materia.getNombre();
        if ( nombre == null ) {
            return null;
        }
        return nombre;
    }

    protected Materia profesorDtoToMateria(ProfesorDto profesorDto) {
        if ( profesorDto == null ) {
            return null;
        }

        Materia.MateriaBuilder materia = Materia.builder();

        materia.nombre( profesorDto.getMateriaEncargado() );

        return materia.build();
    }
}
