//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.service;

import com.example.demo.model.Aluno;
import com.example.demo.model.Disciplina;
import com.example.demo.repository.DisciplinaRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private AlunoService alunoService;

    public DisciplinaService() {
    }

    public Disciplina save(Disciplina disciplina) {
        return (Disciplina)this.disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> findAll() {
        return this.disciplinaRepository.findAll();
    }

    public Disciplina alocarAluno(Long disciplinaId, Long alunoId) {
        Disciplina disciplina = (Disciplina)this.disciplinaRepository.findById(disciplinaId).orElseThrow(() -> {
            return new RuntimeException("Disciplina não encontrada com ID: " + disciplinaId);
        });
        Aluno aluno = this.alunoService.findById(alunoId);
        disciplina.getAlunos().add(aluno);
        return (Disciplina)this.disciplinaRepository.save(disciplina);
    }

    public Disciplina atribuirNota(Long disciplinaId, Long alunoId, Double nota) {
        Disciplina disciplina = (Disciplina)this.disciplinaRepository.findById(disciplinaId).orElseThrow(() -> {
            return new RuntimeException("Disciplina não encontrada com ID: " + disciplinaId);
        });
        Aluno aluno = this.alunoService.findById(alunoId);
        disciplina.getNotas().put(aluno, nota);
        return (Disciplina)this.disciplinaRepository.save(disciplina);
    }

    public List<Aluno> listarAprovados(Long disciplinaId) {
        Disciplina disciplina = (Disciplina)this.disciplinaRepository.findById(disciplinaId).orElseThrow(() -> {
            return new RuntimeException("Disciplina não encontrada com ID: " + disciplinaId);
        });
        return (List)disciplina.getNotas().entrySet().stream().filter((entry) -> {
            return (Double)entry.getValue() >= 7.0;
        }).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public List<Aluno> listarReprovados(Long disciplinaId) {
        Disciplina disciplina = (Disciplina)this.disciplinaRepository.findById(disciplinaId).orElseThrow(() -> {
            return new RuntimeException("Disciplina não encontrada com ID: " + disciplinaId);
        });
        return (List)disciplina.getNotas().entrySet().stream().filter((entry) -> {
            return (Double)entry.getValue() < 7.0;
        }).map(Map.Entry::getKey).collect(Collectors.toList());
    }
}
