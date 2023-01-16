package br.com.homeoffice.apigerirpessoas.repositories;


import br.com.homeoffice.apigerirpessoas.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}

