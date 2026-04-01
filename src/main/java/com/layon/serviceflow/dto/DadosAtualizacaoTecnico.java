package com.layon.serviceflow.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTecnico(
        @NotNull
        Long id,
        String nome,
        String especialidade
) {
}