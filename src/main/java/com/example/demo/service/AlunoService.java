//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.service;

import com.example.demo.model.Aluno;
import com.example.demo.repository.AlunoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public AlunoService() {
    }

    public Aluno save(Aluno aluno) {
        return (Aluno)this.alunoRepository.save(aluno);
    }

    public List<Aluno> findAll() {
        return this.alunoRepository.findAll();
    }

    public Aluno findById(Long id) {
        return (Aluno)this.alunoRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Aluno não encontrado com ID: " + id);
        });
    }
}
