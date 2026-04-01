package com.layon.serviceflow.entity;

import com.layon.serviceflow.dto.DadosAtualizacaoTecnico;
import com.layon.serviceflow.dto.DadosCadastroTecnico;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_tecnicos")

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String matricula;

    private String especialidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    private boolean ativo = true;
    public Tecnico() {
    }
    public Tecnico(DadosCadastroTecnico dados) {
        this.nome = dados.nome();
        this.matricula = dados.matricula();
        this.especialidade = dados.especialidade();
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizacaoTecnico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.especialidade() != null) {
            this.especialidade = dados.especialidade();
        }
    }
    public void excluir() {
        this.ativo = false;
    }
}