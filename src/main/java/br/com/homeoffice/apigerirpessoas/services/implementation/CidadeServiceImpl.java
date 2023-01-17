package br.com.homeoffice.apigerirpessoas.services.implementation;

import br.com.homeoffice.apigerirpessoas.domain.Cidade;
import br.com.homeoffice.apigerirpessoas.domain.dto.CidadeDTO;
import br.com.homeoffice.apigerirpessoas.repositories.CidadeRepository;
import br.com.homeoffice.apigerirpessoas.services.CidadeService;
import br.com.homeoffice.apigerirpessoas.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeServiceImpl implements CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public Cidade findById(Long id) {
        Optional<Cidade> obj = cidadeRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado no id: " + id));
    }

    @Override
    public List<Cidade> findAll() {
        return cidadeRepository.findAll();
    }

    @Override
    public Cidade create(CidadeDTO obj) {
        return cidadeRepository.save(mapper.map(obj, Cidade.class));
    }

    @Override
    public Cidade update(CidadeDTO obj) {
        return cidadeRepository.save(mapper.map(obj, Cidade.class));
    }

    @Override
    public void delete(Long id) {
        findById(id);
        cidadeRepository.deleteById(id);
    }
}
