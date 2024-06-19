package com.example.demo.controller;

import com.example.demo.model.Disciplina;
import com.example.demo.service.DisciplinaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DisciplinaController.class)
public class DisciplinaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DisciplinaService disciplinaService;

    @InjectMocks
    private DisciplinaController disciplinaController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvarDisciplina() throws Exception {
        Disciplina disciplina = new Disciplina();
        disciplina.setId(1L);
        disciplina.setNome("Matemática");
        disciplina.setCodigo("MAT101");

        when(disciplinaService.save(any(Disciplina.class))).thenReturn(disciplina);

        mockMvc.perform(post("/disciplinas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(disciplina)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Matemática"))
                .andExpect(jsonPath("$.codigo").value("MAT101"));

        verify(disciplinaService, times(1)).save(any(Disciplina.class));
        verifyNoMoreInteractions(disciplinaService);
    }

    @Test
    public void testListarDisciplinas() throws Exception {
        Disciplina disciplina1 = new Disciplina();
        disciplina1.setId(1L);
        disciplina1.setNome("Matemática");
        disciplina1.setCodigo("MAT101");

        Disciplina disciplina2 = new Disciplina();
        disciplina2.setId(2L);
        disciplina2.setNome("Física");
        disciplina2.setCodigo("FIS102");

        List<Disciplina> disciplinas = Arrays.asList(disciplina1, disciplina2);

        when(disciplinaService.findAll()).thenReturn(disciplinas);

        mockMvc.perform(get("/disciplinas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Matemática"))
                .andExpect(jsonPath("$[0].codigo").value("MAT101"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].nome").value("Física"))
                .andExpect(jsonPath("$[1].codigo").value("FIS102"));

        verify(disciplinaService, times(1)).findAll();
        verifyNoMoreInteractions(disciplinaService);
    }

    // Método para converter objeto Java em JSON
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
