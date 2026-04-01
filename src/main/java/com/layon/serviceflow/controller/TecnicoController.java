package com.layon.serviceflow.controller;

import com.layon.serviceflow.dto.DadosAtualizacaoTecnico;
import com.layon.serviceflow.dto.DadosCadastroTecnico;
import com.layon.serviceflow.dto.DadosListagemTecnico;
import com.layon.serviceflow.entity.Tecnico;
import com.layon.serviceflow.repository.TecnicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoRepository repository;
    @PostMapping
    @Transactional // Importante para garantir a persistência correta
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTecnico dados, UriComponentsBuilder uriBuilder) {
        // Convertendo o DTO para a Entity
        var tecnico = new Tecnico();
        tecnico.setNome(dados.nome());
        tecnico.setMatricula(dados.matricula());
        tecnico.setEspecialidade(dados.especialidade());
        tecnico.setAtivo(true);

        repository.save(tecnico);

        var uri = uriBuilder.path("/tecnicos/{id}").buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.created(uri).body(tecnico);
    }
    @GetMapping
    public ResponseEntity<Page<DadosListagemTecnico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemTecnico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var tecnico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemTecnico(tecnico));
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTecnico dados) {
        var tecnico = repository.getReferenceById(dados.id());
        tecnico.atualizarInformacoes(dados); // Delegando a lógica para a Entity

        return ResponseEntity.ok(new DadosListagemTecnico(tecnico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var tecnico = repository.getReferenceById(id);
        tecnico.excluir();

        return ResponseEntity.noContent().build();
    }
}