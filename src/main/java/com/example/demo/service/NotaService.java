package com.example.demo.service;

import com.example.demo.model.Aluno;
import com.example.demo.model.Disciplina;
import com.example.demo.model.Nota;
import com.example.demo.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    @Transactional
    public boolean atribuirNota(Long disciplinaId, Long alunoId, Double nota) {
        Disciplina disciplina = new Disciplina();
        disciplina.setId(disciplinaId);

        Aluno aluno = new Aluno();
        aluno.setId(alunoId);

        Nota notaExistente = (Nota) notaRepository.findByDisciplinaAndAluno(disciplina, aluno);
        if (notaExistente != null) {
            notaExistente.setValor(nota);
        } else {
            Nota novaNota = new Nota();
            novaNota.setDisciplina(disciplina);
            novaNota.setAluno(aluno);
            novaNota.setValor(nota);
            notaRepository.save(novaNota);
        }
        return true; // ou você pode ajustar para retornar um resultado apropriado conforme sua lógica
    }

    public List<Nota> findAprovados(Disciplina disciplina) {
        return notaRepository.findByDisciplinaAndValorGreaterThanEqual(disciplina, 7.0);
    }

    public List<Nota> findReprovados(Disciplina disciplina) {
        return notaRepository.findByDisciplinaAndValorLessThan(disciplina, 7.0);
    }

    public List<Nota> listarNotas() {
        return notaRepository.findAll();
    }

    public Nota save(Nota nota) {
        return notaRepository.save(nota);
    }
}
