package br.com.homeoffice.apigerirpessoas.services.implementation;

import br.com.homeoffice.apigerirpessoas.domain.Endereco;
import br.com.homeoffice.apigerirpessoas.domain.PessoaEndereco;
import br.com.homeoffice.apigerirpessoas.domain.dto.PessoaEnderecoDTO;
import br.com.homeoffice.apigerirpessoas.repositories.PessoaEnderecoRepository;
import br.com.homeoffice.apigerirpessoas.services.PessoaEnderecoService;
import br.com.homeoffice.apigerirpessoas.services.exceptions.ObjectNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoasEnderecoServiceImpl implements PessoaEnderecoService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private PessoaEnderecoRepository pessoaEnderecoRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public PessoaEndereco findById(Long id) {
        Optional<PessoaEndereco> obj = pessoaEnderecoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado no id: " + id));
    }

    @Override
    public List<PessoaEndereco> findAll() {
        return pessoaEnderecoRepository.findAll();
    }

    @Override
    public PessoaEndereco create(PessoaEnderecoDTO obj) {
        return pessoaEnderecoRepository.save(mapper.map(obj, PessoaEndereco.class));
    }

    @Override
    public PessoaEndereco update(PessoaEnderecoDTO obj) {
        return pessoaEnderecoRepository.save(mapper.map(obj, PessoaEndereco.class));
    }

    @Override
    public void delete(Long id) {
        findById(id);
        pessoaEnderecoRepository.deleteById(id);
    }

    @Override
    public List<Endereco> FindByPessoaNome(String nome) {
            List<Endereco> list = em.createNativeQuery(
                    "SELECT TB_ENDERECO.ID AS ID, TB_PESSOA.NOME AS PESSOA," +
                            "LOGRADOURO, NUMERO, " +
                            "COMPLEMENTO, TB_CIDADE.NOME AS CIDADE,TB_CIDADE.UF, " +
                            "CASE TB_PESSOA_ENDERECO.ENDERECO_STATUS \n" +
                            "WHEN 0 THEN 'PRINCIPAL' \n" +
                            "ELSE 'SECUNDARIO' \n" +
                            "END AS STATUS \n" +
                            "FROM TB_PESSOA  \n" +
                            "LEFT JOIN TB_PESSOA_ENDERECO ON TB_PESSOA_ENDERECO.PESSOA_ID = TB_PESSOA.ID \n" +
                            "LEFT JOIN TB_ENDERECO ON TB_PESSOA_ENDERECO.ENDERECO_ID = TB_ENDERECO.ID \n" +
                            "LEFT JOIN TB_CIDADE ON TB_ENDERECO.ID= TB_CIDADE.ID \n" +
                            "WHERE LOWER(TB_PESSOA.NOME) LIKE LOWER('%"+ nome +"%') \n" +
                            "ORDER BY TB_ENDERECO.ID").getResultList();
            return list;

    }
}
