package com.layon.serviceflow.service;

import com.layon.serviceflow.dto.*;
import com.layon.serviceflow.entity.Tecnico;
import com.layon.serviceflow.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico cadastrar(DadosCadastroTecnico dados) {
        var tecnico = new Tecnico();
        tecnico.setNome(dados.nome());
        tecnico.setMatricula(dados.matricula());
        tecnico.setEspecialidade(dados.especialidade());
        tecnico.setAtivo(true);

        return repository.save(tecnico);
    }

    public Page<DadosListagemTecnico> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemTecnico::new);
    }

    public Tecnico detalhar(Long id) {
        return repository.getReferenceById(id);
    }

    public Tecnico atualizar(DadosAtualizacaoTecnico dados) {
        var tecnico = repository.getReferenceById(dados.id());
        tecnico.atualizarInformacoes(dados);
        return tecnico;
    }

    public void excluir(Long id) {
        var tecnico = repository.getReferenceById(id);
        tecnico.excluir();
    }
}