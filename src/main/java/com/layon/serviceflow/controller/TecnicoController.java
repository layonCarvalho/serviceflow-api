package com.layon.serviceflow.controller;

import com.layon.serviceflow.dto.*;
import com.layon.serviceflow.service.TecnicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/tecnicos")
@Tag(name = "Técnicos", description = "Operações de gerenciamento de técnicos da ServiceFlow")
public class TecnicoController {

    @Autowired
    private TecnicoService service;

    @PostMapping
    @Transactional
    @Operation(summary = "Cadastra um novo técnico", description = "Cria um registro de técnico no sistema e retorna os dados cadastrados.")
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTecnico dados, UriComponentsBuilder uriBuilder) {
        var tecnico = service.cadastrar(dados);
        var uri = uriBuilder.path("/tecnicos/{id}").buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosListagemTecnico(tecnico));
    }

    @GetMapping
    @Operation(summary = "Lista técnicos ativos", description = "Retorna uma lista paginada de todos os técnicos que não foram excluídos logicamente.")
    public ResponseEntity<Page<DadosListagemTecnico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = service.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalha um técnico", description = "Busca as informações completas de um técnico específico através do seu ID.")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var tecnico = service.detalhar(id);
        return ResponseEntity.ok(new DadosListagemTecnico(tecnico));
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Atualiza dados do técnico", description = "Permite a alteração de informações como nome e especialidade do técnico.")
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTecnico dados) {
        var tecnico = service.atualizar(dados);
        return ResponseEntity.ok(new DadosListagemTecnico(tecnico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Exclusão lógica de técnico", description = "Inativa o cadastro do técnico no sistema sem removê-lo fisicamente do banco de dados.")
    public ResponseEntity excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }}