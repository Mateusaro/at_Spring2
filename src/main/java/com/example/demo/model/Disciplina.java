//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapKeyJoinColumn;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
public class Disciplina {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String nome;
    private String codigo;
    @ManyToMany(
            mappedBy = "disciplinas"
    )
    @JsonManagedReference
    private Set<Aluno> alunos = new HashSet();
    @ElementCollection
    @CollectionTable(
            name = "disciplina_notas",
            joinColumns = {@JoinColumn(
                    name = "disciplina_id"
            )}
    )
    @MapKeyJoinColumn(
            name = "aluno_id"
    )
    @Column(
            name = "nota"
    )
    private Map<Aluno, Double> notas = new HashMap();

    public Disciplina() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Set<Aluno> getAlunos() {
        return this.alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Map<Aluno, Double> getNotas() {
        return this.notas;
    }

    public void setNotas(Map<Aluno, Double> notas) {
        this.notas = notas;
    }
}
