package com.layon.serviceflow.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_tecnicos")
@Getter @Setter
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

    private boolean ativo = true;
}