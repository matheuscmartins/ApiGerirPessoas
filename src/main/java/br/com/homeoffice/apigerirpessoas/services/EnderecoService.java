package br.com.homeoffice.apigerirpessoas.services;

import br.com.homeoffice.apigerirpessoas.domain.Endereco;
import br.com.homeoffice.apigerirpessoas.domain.dto.EnderecoDTO;

import java.util.List;

public interface EnderecoService {
    Endereco findById(Long id);
    List<Endereco> findAll();
    Endereco create(EnderecoDTO obj);
    Endereco update(EnderecoDTO obj);
    void delete(Long id);
}
