package br.com.homeoffice.apigerirpessoas.services.implementation;

import br.com.homeoffice.apigerirpessoas.domain.PessoaEndereco;
import br.com.homeoffice.apigerirpessoas.domain.dto.PessoaEnderecoDTO;
import br.com.homeoffice.apigerirpessoas.repositories.PesssoaEnderecoRepository;
import br.com.homeoffice.apigerirpessoas.services.PessoaEnderecoService;
import br.com.homeoffice.apigerirpessoas.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoasEnderecoServiceImpl implements PessoaEnderecoService {

    @Autowired
    private PesssoaEnderecoRepository pesssoaEnderecoRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public PessoaEndereco findById(Long id) {
        Optional<PessoaEndereco> obj = pesssoaEnderecoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado no id: " + id));
    }

    @Override
    public List<PessoaEndereco> findByIdPessoa(PessoaEnderecoDTO obj) {
        List<PessoaEndereco> list = pesssoaEnderecoRepository.findByIdPessoa(obj);
        return list;
    }
    @Override
    public List<PessoaEndereco> findAll() {
        return pesssoaEnderecoRepository.findAll();
    }

    @Override
    public PessoaEndereco create(PessoaEnderecoDTO obj) {
        return pesssoaEnderecoRepository.save(mapper.map(obj, PessoaEndereco.class));
    }

    @Override
    public PessoaEndereco update(PessoaEnderecoDTO obj) {
        return pesssoaEnderecoRepository.save(mapper.map(obj, PessoaEndereco.class));
    }

    @Override
    public void delete(Long id) {
        findById(id);
        pesssoaEnderecoRepository.deleteById(id);
    }
}
