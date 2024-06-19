package com.example.demo.controller;

import com.example.demo.controller.ProfessorController;
import com.example.demo.model.Professor;
import com.example.demo.service.ProfessorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProfessorControllerTest {

    @Autowired
    private ProfessorController professorController;

    @MockBean
    private ProfessorService professorService;

    private MockMvc mockMvc;

    @Test
    public void testListarProfessores() throws Exception {
        // Dados de teste
        Professor professor1 = new Professor(1L, "José", "jose@example.com");
        Professor professor2 = new Professor(2L, "Maria", "maria@example.com");
        List<Professor> professores = Arrays.asList(professor1, professor2);

        // Configuração do Mock para o método do serviço que lista professores
        when(professorService.findAll()).thenReturn(professores);

        // Inicializa o mockMvc com o controller injetado
        mockMvc = MockMvcBuilders.standaloneSetup(professorController).build();

        // Execução da requisição GET para "/professores"
        ResultActions resultActions = mockMvc.perform(get("/professores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("José"))
                .andExpect(jsonPath("$[0].email").value("jose@example.com"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].nome").value("Maria"))
                .andExpect(jsonPath("$[1].email").value("maria@example.com"));

        // Verificações
        verify(professorService, times(1)).findAll();
        verifyNoMoreInteractions(professorService);
    }
}
