package com.layon.serviceflow.controller;

import com.layon.serviceflow.entity.Tecnico;
import com.layon.serviceflow.repository.TecnicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Transactional
    public ResponseEntity<Tecnico> cadastrar(@RequestBody @Valid Tecnico tecnico, UriComponentsBuilder uriBuilder) {
        // O repository.save já retorna a entidade persistida com o ID gerado pelo banco
        var tecnicoSalvo = repository.save(tecnico);

        // Constrói a URL para o cabeçalho 'Location'
        var uri = uriBuilder.path("/tecnicos/{id}").buildAndExpand(tecnicoSalvo.getId()).toUri();

        return ResponseEntity.created(uri).body(tecnicoSalvo);
    }
}