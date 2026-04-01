package com.layon.serviceflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroTecnico(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "A matrícula é obrigatória")
        @Pattern(regexp = "\\d{4,10}", message = "A matrícula deve conter de 4 a 10 dígitos numéricos")
        String matricula,

        @NotBlank(message = "A especialidade é obrigatória")
        String especialidade
) {
}