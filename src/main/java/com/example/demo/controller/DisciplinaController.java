//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.controller;

import com.example.demo.dto.DisciplinaDTO;
import com.example.demo.model.Aluno;
import com.example.demo.model.Disciplina;
import com.example.demo.service.DisciplinaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/disciplinas"})
public class DisciplinaController {
    @Autowired
    private DisciplinaService disciplinaService;

    public DisciplinaController() {
    }

    @PostMapping
    public Disciplina save(@RequestBody DisciplinaDTO disciplinaDTO) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(disciplinaDTO.getNome());
        disciplina.setCodigo(disciplinaDTO.getCodigo());
        return this.disciplinaService.save(disciplina);
    }

    @GetMapping
    public List<Disciplina> findAll() {
        return this.disciplinaService.findAll();
    }

    @PostMapping({"/{disciplinaId}/alunos/{alunoId}"})
    public Disciplina alocarAluno(@PathVariable Long disciplinaId, @PathVariable Long alunoId) {
        return this.disciplinaService.alocarAluno(disciplinaId, alunoId);
    }

    @PostMapping({"/{disciplinaId}/alunos/{alunoId}/nota"})
    public Disciplina atribuirNota(@PathVariable Long disciplinaId, @PathVariable Long alunoId, @RequestBody Double nota) {
        return this.disciplinaService.atribuirNota(disciplinaId, alunoId, nota);
    }

    @GetMapping({"/{disciplinaId}/aprovados"})
    public List<Aluno> listarAprovados(@PathVariable Long disciplinaId) {
        return this.disciplinaService.listarAprovados(disciplinaId);
    }

    @GetMapping({"/{disciplinaId}/reprovados"})
    public List<Aluno> listarReprovados(@PathVariable Long disciplinaId) {
        return this.disciplinaService.listarReprovados(disciplinaId);
    }
}
