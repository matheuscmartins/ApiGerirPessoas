package br.com.homeoffice.apigerirpessoas.domain.dto;

import br.com.homeoffice.apigerirpessoas.domain.Cidade;
import br.com.homeoffice.apigerirpessoas.domain.PessoaEndereco;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

    private Long id;
    private String logradouro;
    private String numero;
    private String complemento;
    private Cidade cidade ;
    @JsonIgnore
    private Set<PessoaEndereco> enderecos = new HashSet<>();
}
