package com.layon.serviceflow.controller;

import com.layon.serviceflow.dto.DadosAtualizacaoTecnico;
import com.layon.serviceflow.dto.DadosCadastroTecnico;
import com.layon.serviceflow.dto.DadosListagemTecnico;
import com.layon.serviceflow.service.TecnicoService;
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
public class TecnicoController {

    @Autowired
    private TecnicoService service; // Agora usamos o Service!

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTecnico dados, UriComponentsBuilder uriBuilder) {
        var tecnico = service.cadastrar(dados);
        var uri = uriBuilder.path("/tecnicos/{id}").buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosListagemTecnico(tecnico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTecnico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = service.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var tecnico = service.detalhar(id);
        return ResponseEntity.ok(new DadosListagemTecnico(tecnico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTecnico dados) {
        var tecnico = service.atualizar(dados);
        return ResponseEntity.ok(new DadosListagemTecnico(tecnico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}