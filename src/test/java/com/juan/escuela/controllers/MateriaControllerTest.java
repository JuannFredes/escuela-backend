package com.juan.escuela.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.juan.escuela.dto.MateriaDetailsDto;
import com.juan.escuela.dto.MateriaDto;
import com.juan.escuela.services.MateriaService;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MateriaController.class)
class MateriaControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private MateriaController materiaController;

    @MockBean
    private MateriaService materiaService;

    private ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    private static PodamFactory podamFactory;
    private static ClassInfoStrategy classInfoStrategy;

    @BeforeAll
    static void setupAll(){
        podamFactory = new PodamFactoryImpl();
        classInfoStrategy = DefaultClassInfoStrategy.getInstance();
        podamFactory.setClassStrategy(classInfoStrategy);
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(materiaController).build();
    }

    @Test
    void getAllTest() throws Exception {

        List<MateriaDto> materiaDtos = podamFactory.manufacturePojo(ArrayList.class, MateriaDto.class);

        when(materiaService.getAllMateria()).thenReturn(materiaDtos);

        mockMvc.perform(get("/v1/materias"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].id",is(materiaDtos.get(0).getId())))
                .andExpect(jsonPath("$[0].nombre",is(materiaDtos.get(0).getNombre())))
                .andExpect(jsonPath("$[1].id",is(materiaDtos.get(1).getId())))
                .andExpect(jsonPath("$[1].nombre",is(materiaDtos.get(1).getNombre())));
        verify(materiaService).getAllMateria();

    }

    @Test
    void getMateriaTest() throws Exception {

        MateriaDetailsDto materiaDetailsDto = podamFactory.manufacturePojo(MateriaDetailsDto.class);

        when(materiaService.getMateria(anyInt())).thenReturn(materiaDetailsDto);

        mockMvc.perform(get("/v1/materias/{id}", anyInt()))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(materiaDetailsDto.getId())))
                .andExpect(jsonPath("$.nombre", is(materiaDetailsDto.getNombre())))
                .andExpect(jsonPath("$.cantidad_alumnos", is(materiaDetailsDto.getCantidadAlumnos())))
                .andExpect(jsonPath("$.profesors[0].id", is(materiaDetailsDto.getProfesors().get(0).getId())))
                .andExpect(jsonPath("$.alumnos[2].dni", is(materiaDetailsDto.getAlumnos().get(2).getDni())));
        verify(materiaService).getMateria(anyInt());

    }

    @Test
    void putMateriaTest() throws Exception {

        MateriaDto materiaDto = MateriaDto.builder()
                .id(2)
                .nombre("Materia")
                .build();

        when(materiaService.updateMateria(materiaDto.getId(), materiaDto)).thenReturn(materiaDto);

        mockMvc.perform(put("/v1/materias/{id}", materiaDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(materiaDto)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id",is(materiaDto.getId())))
                .andExpect(jsonPath("$.nombre",is(materiaDto.getNombre())));
        verify(materiaService).updateMateria(materiaDto.getId(), materiaDto);

    }

    @Test
    void saveMateria() throws Exception {

        MateriaDto materia = MateriaDto.builder()
                .id(2)
                .nombre("Materia")
                .build();
        MateriaDto materiaDto = podamFactory.manufacturePojo(MateriaDto.class);

        when(materiaService.saveMateria(materia)).thenReturn(materiaDto);

        mockMvc.perform(post("/v1/materias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(materia)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id",is(materiaDto.getId())))
                .andExpect(jsonPath("$.nombre",is(materiaDto.getNombre())));
        verify(materiaService).saveMateria(materia);

    }

    @Test
    void deleteMateria() throws Exception {

        mockMvc.perform(delete("/v1/materias/{id}", anyInt()))
                .andExpect(status().is(204));
        verify(materiaService).deleteMateria(anyInt());

    }
}