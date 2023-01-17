package br.com.homeoffice.apigerirpessoas.services;

import br.com.homeoffice.apigerirpessoas.domain.Cidade;
import br.com.homeoffice.apigerirpessoas.domain.dto.CidadeDTO;

import java.util.List;

public interface CidadeService {
    Cidade findById(Long id);
    List<Cidade> findAll();
    Cidade create(CidadeDTO obj);
    Cidade update(CidadeDTO obj);
    void delete(Long id);
}
