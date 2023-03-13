package com.juan.escuela.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.juan.escuela.dto.ProfesorDto;
import com.juan.escuela.models.Materia;
import com.juan.escuela.models.Profesor;
import com.juan.escuela.services.ProfesorService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DefaultClassInfoStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProfesorController.class)
class ProfesorControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ProfesorController profesorController;

    @MockBean
    private ProfesorService profesorService;

    private ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    private static PodamFactory podamFactory;
    private static ClassInfoStrategy classInfoStrategy;

    @BeforeAll
    static void setUpAll() {
        podamFactory = new PodamFactoryImpl();
        classInfoStrategy = DefaultClassInfoStrategy.getInstance();
        podamFactory.setClassStrategy(classInfoStrategy);
    }

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(profesorController).build();
    }

    @Test
    void getAllProfesorTest() throws Exception {

        List<ProfesorDto> profesorDtos = podamFactory.manufacturePojo(ArrayList.class, ProfesorDto.class);

        when(profesorService.getAllProfesores()).thenReturn(profesorDtos);

        mockMvc.perform(get("/v1/profesores"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].id",is(profesorDtos.get(0).getId())))
                .andExpect(jsonPath("$[0].nombre",is(profesorDtos.get(0).getNombre())))
                .andExpect(jsonPath("$[1].id",is(profesorDtos.get(1).getId())))
                .andExpect(jsonPath("$[1].age",is(profesorDtos.get(1).getAge())));
        verify(profesorService).getAllProfesores();

    }

    @Test
    void getProfesor() throws Exception {

        ProfesorDto profesorDto = podamFactory.manufacturePojo(ProfesorDto.class);

        when(profesorService.getProfesorById(anyInt())).thenReturn(profesorDto);

        mockMvc.perform(get("/v1/profesores/{id}", anyInt()))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(profesorDto.getId())))
                .andExpect(jsonPath("$.apellido", is(profesorDto.getApellido())));
        verify(profesorService).getProfesorById(anyInt());

    }

    @Test
    void updateProfesorTest() throws Exception {
        DefaultClassInfoStrategy.getInstance()
                .addExcludedField(Materia.class, "profesors")
                .addExcludedField(Materia.class, "materiaAlumnos");

        Profesor profesor = podamFactory.manufacturePojo(Profesor.class);
        ProfesorDto profesorDto = podamFactory.manufacturePojo(ProfesorDto.class);

        when(profesorService.updateProfesorById(profesor)).thenReturn(profesorDto);

        mockMvc.perform(put("/v1/profesores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(profesor)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(profesorDto.getId())))
                .andExpect(jsonPath("$.email", is(profesorDto.getEmail())));
        verify(profesorService).updateProfesorById(profesor);

        DefaultClassInfoStrategy.getInstance()
                .removeExcludedField(Materia.class, "profesors")
                .removeExcludedField(Materia.class, "materiaAlumnos");
    }

    @Test
    void saveProfesorTest() throws Exception {
        DefaultClassInfoStrategy.getInstance()
                .addExcludedField(Materia.class, "profesors")
                .addExcludedField(Materia.class, "materiaAlumnos");

        Profesor profesor = podamFactory.manufacturePojo(Profesor.class);
        ProfesorDto profesorDto = podamFactory.manufacturePojo(ProfesorDto.class);

        when(profesorService.saveProfesor(profesor)).thenReturn(profesorDto);

        mockMvc.perform(post("/v1/profesores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(profesor)))
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.id", is(profesorDto.getId())))
                .andExpect(jsonPath("$.telefono", is(profesorDto.getTelefono())));
        verify(profesorService).saveProfesor(profesor);

        DefaultClassInfoStrategy.getInstance()
                .removeExcludedField(Materia.class, "profesors")
                .removeExcludedField(Materia.class, "materiaAlumnos");
    }

    @Test
    void deleteProfesor() throws Exception {

        mockMvc.perform(delete("/v1/profesores/{id}", anyInt()))
                .andExpect(status().is(204));
        verify(profesorService).deleteProfesorByid(anyInt());

    }
}