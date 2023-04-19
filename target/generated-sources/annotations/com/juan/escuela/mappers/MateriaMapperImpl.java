package com.juan.escuela.mappers;

import com.juan.escuela.dto.AlumnoDto;
import com.juan.escuela.dto.MateriaDetailsDto;
import com.juan.escuela.dto.MateriaDto;
import com.juan.escuela.dto.MateriaNotaDto;
import com.juan.escuela.models.Alumno;
import com.juan.escuela.models.Materia;
import com.juan.escuela.models.MateriaAlumno;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-19T11:59:30-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Ubuntu)"
)
@Component
public class MateriaMapperImpl implements MateriaMapper {

    @Autowired
    private AlumnoMapper alumnoMapper;
    @Autowired
    private ProfesorMapper profesorMapper;

    @Override
    public MateriaDto toMateriaDto(Materia materia) {
        if ( materia == null ) {
            return null;
        }

        MateriaDto.MateriaDtoBuilder materiaDto = MateriaDto.builder();

        materiaDto.id( materia.getId() );
        materiaDto.nombre( materia.getNombre() );

        return materiaDto.build();
    }

    @Override
    public Materia toMateria(MateriaDto materiaDto) {
        if ( materiaDto == null ) {
            return null;
        }

        Materia.MateriaBuilder materia = Materia.builder();

        materia.id( materiaDto.getId() );
        materia.nombre( materiaDto.getNombre() );

        return materia.build();
    }

    @Override
    public List<MateriaDto> toListMateriaDto(List<Materia> materias) {
        if ( materias == null ) {
            return null;
        }

        List<MateriaDto> list = new ArrayList<MateriaDto>( materias.size() );
        for ( Materia materia : materias ) {
            list.add( toMateriaDto( materia ) );
        }

        return list;
    }

    @Override
    public Alumno toAlumno(MateriaAlumno materiaAlumno) {
        if ( materiaAlumno == null ) {
            return null;
        }

        Alumno.AlumnoBuilder alumno = Alumno.builder();

        alumno.id( materiaAlumnoAlumnoId( materiaAlumno ) );
        alumno.nombre( materiaAlumnoAlumnoNombre( materiaAlumno ) );
        alumno.apellido( materiaAlumnoAlumnoApellido( materiaAlumno ) );
        alumno.dni( materiaAlumnoAlumnoDni( materiaAlumno ) );
        alumno.telefono( materiaAlumnoAlumnoTelefono( materiaAlumno ) );
        alumno.celular( materiaAlumnoAlumnoCelular( materiaAlumno ) );
        alumno.email( materiaAlumnoAlumnoEmail( materiaAlumno ) );
        alumno.fechaNacimiento( materiaAlumnoAlumnoFechaNacimiento( materiaAlumno ) );
        alumno.sexo( materiaAlumnoAlumnoSexo( materiaAlumno ) );
        alumno.direccion( materiaAlumnoAlumnoDireccion( materiaAlumno ) );
        alumno.create( materiaAlumnoAlumnoCreate( materiaAlumno ) );
        alumno.numeroEmergencia( materiaAlumnoAlumnoNumeroEmergencia( materiaAlumno ) );
        alumno.anio( materiaAlumnoAlumnoAnio( materiaAlumno ) );
        alumno.division( materiaAlumnoAlumnoDivision( materiaAlumno ) );
        List<MateriaAlumno> materiaAlumnos = materiaAlumnoAlumnoMateriaAlumnos( materiaAlumno );
        List<MateriaAlumno> list = materiaAlumnos;
        if ( list != null ) {
            alumno.materiaAlumnos( new ArrayList<MateriaAlumno>( list ) );
        }

        return alumno.build();
    }

    @Override
    public List<Alumno> toListAlumno(List<MateriaAlumno> materiaAlumnos) {
        if ( materiaAlumnos == null ) {
            return null;
        }

        List<Alumno> list = new ArrayList<Alumno>( materiaAlumnos.size() );
        for ( MateriaAlumno materiaAlumno : materiaAlumnos ) {
            list.add( toAlumno( materiaAlumno ) );
        }

        return list;
    }

    @Override
    public MateriaDetailsDto toMateriaDetailsDto(Materia materia) {
        if ( materia == null ) {
            return null;
        }

        MateriaDetailsDto.MateriaDetailsDtoBuilder materiaDetailsDto = MateriaDetailsDto.MateriaDetailsBuilder();

        materiaDetailsDto.alumnos( materiaAlumnoListToAlumnoDtoList( materia.getMateriaAlumnos() ) );
        materiaDetailsDto.id( materia.getId() );
        materiaDetailsDto.nombre( materia.getNombre() );
        materiaDetailsDto.profesors( profesorMapper.toListProfesorDto( materia.getProfesors() ) );

        materiaDetailsDto.cantidadAlumnos( materia.getMateriaAlumnos().size() );

        return materiaDetailsDto.build();
    }

    @Override
    public MateriaNotaDto toMateriaNotaDto(MateriaAlumno materiaAlumno) {
        if ( materiaAlumno == null ) {
            return null;
        }

        MateriaNotaDto.MateriaNotaDtoBuilder materiaNotaDto = MateriaNotaDto.MateriaNotaBuilder();

        materiaNotaDto.id( materiaAlumnoMateriaId( materiaAlumno ) );
        materiaNotaDto.nombre( materiaAlumnoMateriaNombre( materiaAlumno ) );
        materiaNotaDto.nota( materiaAlumno.getNota() );

        return materiaNotaDto.build();
    }

    private int materiaAlumnoAlumnoId(MateriaAlumno materiaAlumno) {
        if ( materiaAlumno == null ) {
            return 0;
        }
        Alumno alumno = materiaAlumno.getAlumno();
        if ( alumno == null ) {
            return 0;
        }
        int id = alumno.getId();
        return id;
    }

    private String materiaAlumnoAlumnoNombre(MateriaAlumno materiaAlumno) {
        if ( materiaAlumno == null ) {
            return null;
        }
        Alumno alumno = materiaAlumno.getAlumno();
        if ( alumno == null ) {
            return null;
        }
        String nombre = alumno.getNombre();
        if ( nombre == null ) {
            return null;
        }
        return nombre;
    }

    private String materiaAlumnoAlumnoApellido(MateriaAlumno materiaAlumno) {
        if ( materiaAlumno == null ) {
            return null;
        }
        Alumno alumno = materiaAlumno.getAlumno();
        if ( alumno == null ) {
            return null;
        }
        String apellido = alumno.getApellido();
        if ( apellido == null ) {
            return null;
        }
        return apellido;
    }

    private String materiaAlumnoAlumnoDni(MateriaAlumno materiaAlumno) {
        if ( materiaAlumno == null ) {
            return null;
        }
        Alumno alumno = materiaAlumno.getAlumno();
        if ( alumno == null ) {
            return null;
        }
        String dni = alumno.getDni();
        if ( dni == null ) {
            return null;
        }
        return dni;
    }

    private String materiaAlumnoAlumnoTelefono(MateriaAlumno materiaAlumno) {
        if ( materiaAlumno == null ) {
            return null;
        }
        Alumno alumno = materiaAlumno.getAlumno();
        if ( alumno == null ) {
            return null;
        }
        String telefono = alumno.getTelefono();
        if ( telefono == null ) {
            return null;
        }
        return telefono;
    }

    private String materiaAlumnoAlumnoCelular(MateriaAlumno materiaAlumno) {
        if ( materiaAlumno == null ) {
            return null;
        }
        Alumno alumno = materiaAlumno.getAlumno();
        if ( alumno == null ) {
            return null;
        }
        String celular = alumno.getCelular();
        if ( celular == null ) {
            return null;
        }
        return celular;
    }

    private String materiaAlumnoAlumnoEmail(MateriaAlumno materiaAlumno) {
        if ( materiaAlumno == null ) {
            return null;
        }
        Alumno alumno = materiaAlumno.getAlumno();
        if ( alumno == null ) {
            return null;
        }
        String email = alumno.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    private LocalDate materiaAlumnoAlumnoFechaNacimiento(MateriaAlumno materiaAlumno) {
        if ( materiaAlumno == null ) {
            return null;
        }
        Alumno alumno = materiaAlumno.getAlumno();
        if ( alumno == null ) {
            return null;
        }
        LocalDate fechaNacimiento = alumno.getFechaNacimiento();
        if ( fechaNacimiento == null ) {
            return null;
        }
        return fechaNacimiento;
    }

    private char materiaAlumnoAlumnoSexo(MateriaAlumno materiaAlumno) {
        if ( materiaAlumno == null ) {
            return 0;
        }
        Alumno alumno = materiaAlumno.getAlumno();
        if ( alumno == null ) {
            return 0;
        }
        char sexo = alumno.getSexo();
        return sexo;
    }

    private String materiaAlumnoAlumnoDireccion(MateriaAlumno materiaAlumno) {
        if ( materiaAlumno == null ) {
            return null;
        }
        Alumno alumno = materiaAlumno.getAlumno();
        if ( alumno == null ) {
            return null;
        }
        String direccion = alumno.getDireccion();
        if ( direccion == null ) {
            return null;
        }
        return direccion;
    }

    private LocalDateTime materiaAlumnoAlumnoCreate(MateriaAlumno materiaAlumno) {
        if ( materiaAlumno == null ) {
            return null;
        }
        Alumno alumno = materiaAlumno.getAlumno();
        if ( alumno == null ) {
            return null;
        }
        LocalDateTime create = alumno.getCreate();
        if ( create == null ) {
            return null;
        }
        return create;
    }

    private String materiaAlumnoAlumnoNumeroEmergencia(MateriaAlumno materiaAlumno) {
        if ( materiaAlumno == null ) {
            return null;
        }
        Alumno alumno = materiaAlumno.getAlumno();
        if ( alumno == null ) {
            return null;
        }
        String numeroEmergencia = alumno.getNumeroEmergencia();
        if ( numeroEmergencia == null ) {
            return null;
        }
        return numeroEmergencia;
    }

    private Short materiaAlumnoAlumnoAnio(MateriaAlumno materiaAlumno) {
        if ( materiaAlumno == null ) {
            return null;
        }
        Alumno alumno = materiaAlumno.getAlumno();
        if ( alumno == null ) {
            return null;
        }
        Short anio = alumno.getAnio();
        if ( anio == null ) {
            return null;
        }
        return anio;
    }

    private Short materiaAlumnoAlumnoDivision(MateriaAlumno materiaAlumno) {
        if ( materiaAlumno == null ) {
            return null;
        }
        Alumno alumno = materiaAlumno.getAlumno();
        if ( alumno == null ) {
            return null;
        }
        Short division = alumno.getDivision();
        if ( division == null ) {
            return null;
        }
        return division;
    }

    private List<MateriaAlumno> materiaAlumnoAlumnoMateriaAlumnos(MateriaAlumno materiaAlumno) {
        if ( materiaAlumno == null ) {
            return null;
        }
        Alumno alumno = materiaAlumno.getAlumno();
        if ( alumno == null ) {
            return null;
        }
        List<MateriaAlumno> materiaAlumnos = alumno.getMateriaAlumnos();
        if ( materiaAlumnos == null ) {
            return null;
        }
        return materiaAlumnos;
    }

    protected List<AlumnoDto> materiaAlumnoListToAlumnoDtoList(List<MateriaAlumno> list) {
        if ( list == null ) {
            return null;
        }

        List<AlumnoDto> list1 = new ArrayList<AlumnoDto>( list.size() );
        for ( MateriaAlumno materiaAlumno : list ) {
            list1.add( alumnoMapper.toAlumnoDto( toAlumno( materiaAlumno ) ) );
        }

        return list1;
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
}
