package br.com.homeoffice.apigerirpessoas.services.implementation;

import br.com.homeoffice.apigerirpessoas.domain.Endereco;
import br.com.homeoffice.apigerirpessoas.domain.dto.EnderecoDTO;
import br.com.homeoffice.apigerirpessoas.repositories.EnderecoRepository;
import br.com.homeoffice.apigerirpessoas.services.EnderecoService;
import br.com.homeoffice.apigerirpessoas.services.exceptions.DataIntegrityViolationException;
import br.com.homeoffice.apigerirpessoas.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Endereco findById(Long id) {
        Optional<Endereco> obj = enderecoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado no id: " + id));
    }

    @Override
    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    @Override
    public Endereco create(EnderecoDTO obj) {
        try {
            return enderecoRepository.save(mapper.map(obj, Endereco.class));
        } catch (RuntimeException exception) {
            throw new DataIntegrityViolationException(exception.getMessage());
        }
    }

    @Override
    public Endereco update(EnderecoDTO obj) {
        try {
            return enderecoRepository.save(mapper.map(obj, Endereco.class));
        } catch (RuntimeException exception) {
            throw new DataIntegrityViolationException(exception.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        findById(id);
        enderecoRepository.deleteById(id);
    }
}
