package br.com.homeoffice.apigerirpessoas.repositories;


import br.com.homeoffice.apigerirpessoas.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
