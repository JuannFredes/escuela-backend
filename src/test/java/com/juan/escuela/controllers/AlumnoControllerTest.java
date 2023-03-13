package com.juan.escuela.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.juan.escuela.dto.AlumnoDto;
import com.juan.escuela.dto.AlumnoMateriasDto;
import com.juan.escuela.models.Alumno;
import com.juan.escuela.services.AlumnoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DefaultClassInfoStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AlumnoController.class)
class AlumnoControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private AlumnoController alumnoController;

    @MockBean
    private AlumnoService alumnoService;

    private ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private static PodamFactory podamFactory;
    private static ClassInfoStrategy classInfoStrategy;

    @BeforeAll
    static void setUpAll(){
        podamFactory = new PodamFactoryImpl();
        classInfoStrategy = DefaultClassInfoStrategy.getInstance();
        podamFactory.setClassStrategy(classInfoStrategy);
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(alumnoController).build();
    }

    @Test
    void getAlumnosTest() throws Exception {
        List<AlumnoDto> alumnoDtos = podamFactory.manufacturePojo(ArrayList.class, AlumnoDto.class);

        when(alumnoService.getAllAlumnos()).thenReturn(alumnoDtos);

        mockMvc.perform(get("/v1/alumnos"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].id",is(alumnoDtos.get(0).getId())))
                .andExpect(jsonPath("$[0].nombre",is(alumnoDtos.get(0).getNombre())))
                .andExpect(jsonPath("$[1].id",is(alumnoDtos.get(1).getId())))
                .andExpect(jsonPath("$[1].age",is(alumnoDtos.get(1).getAge())));
        verify(alumnoService).getAllAlumnos();

    }

    @Test
    void getAlumnoTest() throws Exception {
        AlumnoMateriasDto alumnoMateriasDto = podamFactory.manufacturePojo(AlumnoMateriasDto.class);

        when(alumnoService.getAlumnoById(anyInt())).thenReturn(alumnoMateriasDto);

        mockMvc.perform(get("/v1/alumnos/{id}", anyInt()))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(alumnoMateriasDto.getId())))
                .andExpect(jsonPath("$.dni", is(alumnoMateriasDto.getDni())))
                .andExpect(jsonPath("$.materias[0].id", is(alumnoMateriasDto.getMaterias().get(0).getId())))
                .andExpect(jsonPath("$.materias[1].nombre", is(alumnoMateriasDto.getMaterias().get(1).getNombre())));
        verify(alumnoService).getAlumnoById(anyInt());

    }

    @Test
    void updateAlumnoTest() throws Exception {
        DefaultClassInfoStrategy.getInstance()
                .addExcludedField(Alumno.class, "materiaAlumnos");

        Alumno alumno = podamFactory.manufacturePojo(Alumno.class);

        AlumnoDto alumnoDto = podamFactory.manufacturePojo(AlumnoDto.class);

        when(alumnoService.putAlumnoById(alumno)).thenReturn(alumnoDto);

        mockMvc.perform(put("/v1/alumnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(alumno)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(alumnoDto.getId())))
                .andExpect(jsonPath("$.dni", is(alumnoDto.getDni())));
        verify(alumnoService).putAlumnoById(alumno);
        DefaultClassInfoStrategy.getInstance()
                .removeExcludedField(Alumno.class, "materiaAlumnos");

    }

    @Test
    void saveAlumno() throws Exception {
        DefaultClassInfoStrategy.getInstance()
                .addExcludedField(Alumno.class, "materiaAlumnos");

        Alumno alumno = podamFactory.manufacturePojo(Alumno.class);

        AlumnoDto alumnoDto = podamFactory.manufacturePojo(AlumnoDto.class);

        when(alumnoService.saveAlumno(alumno)).thenReturn(alumnoDto);

        mockMvc.perform(post("/v1/alumnos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(alumno)))
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.id", is(alumnoDto.getId())))
                .andExpect(jsonPath("$.dni", is(alumnoDto.getDni())));
        verify(alumnoService).saveAlumno(alumno);

        DefaultClassInfoStrategy.getInstance()
                .removeExcludedField(Alumno.class, "materiaAlumnos");
    }

    @Test
    void deleteAlumno() throws Exception {

        mockMvc.perform(delete("/v1/alumnos/{id}", anyInt()))
                .andExpect(status().is(204));
        verify(alumnoService).deleteAlumnoById(anyInt());

    }
}