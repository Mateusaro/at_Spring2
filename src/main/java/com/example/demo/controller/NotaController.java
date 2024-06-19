//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.controller;

import com.example.demo.dto.NotaDTO;
import com.example.demo.model.Disciplina;
import com.example.demo.model.Nota;
import com.example.demo.service.NotaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/notas"})
public class NotaController {
    @Autowired
    private NotaService notaService;

    public NotaController() {
    }

    @PostMapping
    public Nota save(@RequestBody NotaDTO notaDTO) {
        Nota nota = new Nota();
        nota.setValor(notaDTO.getValor());
        return this.notaService.save(nota);
    }

    @GetMapping({"/aprovados"})
    public List<Nota> findAprovados(@RequestParam Long disciplinaId) {
        Disciplina disciplina = new Disciplina();
        disciplina.setId(disciplinaId);
        return this.notaService.findAprovados(disciplina);
    }

    @GetMapping({"/reprovados"})
    public List<Nota> findReprovados(@RequestParam Long disciplinaId) {
        Disciplina disciplina = new Disciplina();
        disciplina.setId(disciplinaId);
        return this.notaService.findReprovados(disciplina);
    }
}
