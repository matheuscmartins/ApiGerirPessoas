package br.com.homeoffice.apigerirpessoas.services;

import br.com.homeoffice.apigerirpessoas.domain.Pessoa;
import br.com.homeoffice.apigerirpessoas.domain.dto.PessoaDTO;

import java.util.List;

public interface PessoaService {

    Pessoa findById(Long id);
    List<Pessoa> findAll();
    Pessoa create(PessoaDTO obj);
    Pessoa update(PessoaDTO obj);
    void delete(Long id);
}
