package br.com.homeoffice.apigerirpessoas.services.implementation;

import br.com.homeoffice.apigerirpessoas.domain.Pessoa;
import br.com.homeoffice.apigerirpessoas.domain.dto.PessoaDTO;
import br.com.homeoffice.apigerirpessoas.repositories.PessoaRepository;
import br.com.homeoffice.apigerirpessoas.services.PessoaService;
import br.com.homeoffice.apigerirpessoas.services.exceptions.DataIntegrityViolationException;
import br.com.homeoffice.apigerirpessoas.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoasServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Pessoa findById(Long id) {
        Optional<Pessoa> obj = pessoaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado no id: " + id));
    }

    @Override
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    @Override
    public Pessoa create(PessoaDTO obj) {
        try {
            return pessoaRepository.save(mapper.map(obj, Pessoa.class));
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityViolationException(exception.getMessage());
        }

    }

    @Override
    public Pessoa update(PessoaDTO obj) {
        try {
            return pessoaRepository.save(mapper.map(obj, Pessoa.class));
        } catch (RuntimeException exception) {
            throw new DataIntegrityViolationException(exception.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        findById(id);
        pessoaRepository.deleteById(id);
    }
}
