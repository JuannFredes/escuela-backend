package com.juan.escuela.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.juan.escuela.dto.MateriaNotaDto;
import com.juan.escuela.services.MateriaAlumnoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MateriaAlumnoController.class)
class MateriaAlumnoControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private MateriaAlumnoController materiaAlumnoController;

    @MockBean
    private MateriaAlumnoService materiaAlumnoService;

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
        mockMvc = MockMvcBuilders.standaloneSetup(materiaAlumnoController).build();
    }

    @Test
    void updateMateriaNotaTest() throws Exception {

        MateriaNotaDto materiaNotaDto = podamFactory.manufacturePojo(MateriaNotaDto.class);

        when(materiaAlumnoService.updateMateriaNota(1, materiaNotaDto.getId(), materiaNotaDto)).thenReturn(materiaNotaDto);

        mockMvc.perform(post("/v1/alumnos/{idAlumno}/materias/{idMateria}", 1, materiaNotaDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(materiaNotaDto)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(materiaNotaDto.getId())))
                .andExpect(jsonPath("$.nombre", is(materiaNotaDto.getNombre())))
                .andExpect(jsonPath("$.nota", is((int)materiaNotaDto.getNota())));
        verify(materiaAlumnoService).updateMateriaNota(1, materiaNotaDto.getId(), materiaNotaDto);

    }
}