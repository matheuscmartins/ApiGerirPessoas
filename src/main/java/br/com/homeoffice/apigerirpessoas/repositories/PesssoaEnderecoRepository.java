package br.com.homeoffice.apigerirpessoas.repositories;


import br.com.homeoffice.apigerirpessoas.domain.PessoaEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PesssoaEnderecoRepository extends JpaRepository<PessoaEndereco, Long> {


}
