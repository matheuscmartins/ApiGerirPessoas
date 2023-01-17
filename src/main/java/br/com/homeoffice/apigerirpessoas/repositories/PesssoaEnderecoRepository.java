package br.com.homeoffice.apigerirpessoas.repositories;



import br.com.homeoffice.apigerirpessoas.domain.PessoaEndereco;
import br.com.homeoffice.apigerirpessoas.domain.dto.PessoaEnderecoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PesssoaEnderecoRepository extends JpaRepository<PessoaEndereco, Long> {

    List<PessoaEndereco> findByIdPessoa(PessoaEnderecoDTO pessoaId);
}
