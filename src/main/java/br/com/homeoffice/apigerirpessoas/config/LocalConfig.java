package br.com.homeoffice.apigerirpessoas.config;


import br.com.homeoffice.apigerirpessoas.domain.Cidade;
import br.com.homeoffice.apigerirpessoas.domain.Endereco;
import br.com.homeoffice.apigerirpessoas.domain.Pessoa;
import br.com.homeoffice.apigerirpessoas.domain.PessoaEndereco;
import br.com.homeoffice.apigerirpessoas.domain.enums.EnderecoStatus;
import br.com.homeoffice.apigerirpessoas.repositories.CidadeRepository;
import br.com.homeoffice.apigerirpessoas.repositories.EnderecoRepository;
import br.com.homeoffice.apigerirpessoas.repositories.PessoaRepository;
import br.com.homeoffice.apigerirpessoas.repositories.PesssoaEnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private PesssoaEnderecoRepository pessoaEnderecoRepository;


    @Bean
    public void startDB() {
        Cidade cidade1 = new Cidade(null, "Presidente Prudente", "SP");
        Cidade cidade2 = new Cidade(null, "Presidente Bernardes", "SP");
        Cidade cidade3 = new Cidade(null, "Presidente Venceslau", "SP");
        Cidade cidade4 = new Cidade(null, "Presidente Epitacio", "SP");
        cidadeRepository.saveAll(List.of(cidade1, cidade2, cidade3, cidade4));

        Pessoa pessoa1 = new Pessoa(null, "Maria Cruz", LocalDate.parse("1987-01-28"));
        Pessoa pessoa2 = new Pessoa(null, "Jose Cruz", LocalDate.parse("1978-11-19"));
        Pessoa pessoa3 = new Pessoa(null, "Bob Cruz", LocalDate.parse("2000-04-28"));
        Pessoa pessoa4 = new Pessoa(null, "Alex Green", LocalDate.parse("1998-09-28"));
        pessoaRepository.saveAll(List.of(pessoa1, pessoa2, pessoa3, pessoa4));

        Endereco endereco1 = new Endereco(null, "Joao Filho", "33",
                "", cidade1, null);
        Endereco endereco2 = new Endereco(null, "Joao Ramalho", "66",
                "", cidade2, null);
        Endereco endereco3 = new Endereco(null, "Jose Fernando", "3137",
                "", cidade3, null);
        Endereco endereco4 = new Endereco(null, "Maria Martins", "1898",
                "", cidade1, null);
        Endereco endereco5 = new Endereco(null, "Luiz Garcia", "538",
                "", cidade4, null);
        enderecoRepository.saveAll(List.of(endereco1, endereco2, endereco3, endereco4, endereco5));

        PessoaEndereco pessoaEndereco1 = new PessoaEndereco(null, EnderecoStatus.PRINCIPAL, pessoa1, endereco1);
        PessoaEndereco pessoaEndereco2 = new PessoaEndereco(null, EnderecoStatus.PRINCIPAL, pessoa2, endereco1);
        PessoaEndereco pessoaEndereco3 = new PessoaEndereco(null, EnderecoStatus.PRINCIPAL, pessoa3, endereco1);
        PessoaEndereco pessoaEndereco4 = new PessoaEndereco(null, EnderecoStatus.SECUNDARIO, pessoa1, endereco5);
        PessoaEndereco pessoaEndereco5 = new PessoaEndereco(null, EnderecoStatus.PRINCIPAL, pessoa4, endereco4);
        PessoaEndereco pessoaEndereco6 = new PessoaEndereco(null, EnderecoStatus.SECUNDARIO, pessoa1, endereco2);
        pessoaEnderecoRepository.saveAll(List.of(pessoaEndereco1, pessoaEndereco2, pessoaEndereco3,
                pessoaEndereco4, pessoaEndereco5, pessoaEndereco6));
    }
}
