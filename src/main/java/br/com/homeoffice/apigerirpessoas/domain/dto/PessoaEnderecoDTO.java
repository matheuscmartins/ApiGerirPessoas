package br.com.homeoffice.apigerirpessoas.domain.dto;

import br.com.homeoffice.apigerirpessoas.domain.Endereco;
import br.com.homeoffice.apigerirpessoas.domain.Pessoa;
import br.com.homeoffice.apigerirpessoas.domain.enums.EnderecoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaEnderecoDTO {

    private Long id;
    private EnderecoStatus enderecoStatus;
    private Pessoa pessoa;
    private Endereco endereco;
}
