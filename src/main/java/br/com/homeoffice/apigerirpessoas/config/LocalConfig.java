package br.com.homeoffice.apigerirpessoas.config;


import br.com.homeoffice.apigerirpessoas.domain.Cidade;
import br.com.homeoffice.apigerirpessoas.domain.Pessoa;
import br.com.homeoffice.apigerirpessoas.domain.PessoaEndereco;
import br.com.homeoffice.apigerirpessoas.repositories.CidadeRepository;
import br.com.homeoffice.apigerirpessoas.repositories.EnderecoRepository;
import br.com.homeoffice.apigerirpessoas.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
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
    private PessoaEndereco pessoaEnderecoRepository;

    @Bean
    public void startDB() {
        Cidade cidade1 = new Cidade(null, "Presidente Prudente", "SP", null);
        Cidade cidade2 = new Cidade(null, "Presidente Bernardes", "SP", null);
        Cidade cidade3 = new Cidade(null, "Presidente Venceslau", "SP", null);
        Cidade cidade4 = new Cidade(null, "Presidente Epitacio", "SP", null);
        cidadeRepository.saveAll(List.of(cidade1, cidade2, cidade3, cidade4));

        Pessoa pessoa1 = new Pessoa(null, "Maria Cruz", Instant.parse("23/02/1970"), null);
        Pessoa pessoa2 = new Pessoa(null, "Jose Cruz", Instant.parse("01/10/1975"), null);
        Pessoa pessoa3 = new Pessoa(null, "Bob Cruz", Instant.parse("20/09/1995"), null);
        pessoaRepository.saveAll(List.of(pessoa1, pessoa2, pessoa3));

        //Endereco endereco1 = new Endereco(null,"Joao Filho","33", "",cidade1.setId();,null);


    }
}
