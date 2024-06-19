package com.example.demo.repository;

import com.example.demo.model.Aluno;
import com.example.demo.model.Disciplina;
import com.example.demo.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {

    List<Nota> findByDisciplinaAndAluno(Disciplina disciplina, Aluno aluno);

    List<Nota> findByDisciplinaAndValorGreaterThanEqual(Disciplina disciplina, Double valor);

    List<Nota> findByDisciplinaAndValorLessThan(Disciplina disciplina, Double valor);
}
