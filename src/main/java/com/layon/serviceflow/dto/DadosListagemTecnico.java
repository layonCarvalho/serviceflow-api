package com.layon.serviceflow.dto;

import com.layon.serviceflow.entity.Tecnico;

public record DadosListagemTecnico(Long id, String nome, String matricula, String especialidade) {
    public DadosListagemTecnico(Tecnico tecnico) {
        this(tecnico.getId(), tecnico.getNome(), tecnico.getMatricula(), tecnico.getEspecialidade());
    }
}