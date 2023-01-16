package br.com.homeoffice.apigerirpessoas.repositories;


import br.com.homeoffice.apigerirpessoas.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
