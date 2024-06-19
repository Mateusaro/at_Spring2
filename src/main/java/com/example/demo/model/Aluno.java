//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapKeyJoinColumn;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
public class Aluno {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String endereco;
    @ManyToMany
    @JoinTable(
            name = "disciplina_aluno",
            joinColumns = {@JoinColumn(
                    name = "aluno_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "disciplina_id"
            )}
    )
    @JsonBackReference
    private Set<Disciplina> disciplinas = new HashSet();
    @ElementCollection
    @CollectionTable(
            name = "notas",
            joinColumns = {@JoinColumn(
                    name = "aluno_id"
            )}
    )
    @MapKeyJoinColumn(
            name = "disciplina_id"
    )
    @Column(
            name = "nota"
    )
    private Map<Disciplina, Double> notas = new HashMap();

    public Aluno() {
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

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Set<Disciplina> getDisciplinas() {
        return this.disciplinas;
    }

    public void setDisciplinas(Set<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public Map<Disciplina, Double> getNotas() {
        return this.notas;
    }

    public void setNotas(Map<Disciplina, Double> notas) {
        this.notas = notas;
    }
}
