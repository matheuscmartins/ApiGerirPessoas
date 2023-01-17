package br.com.homeoffice.apigerirpessoas.domain.dto;

import br.com.homeoffice.apigerirpessoas.domain.Cidade;
import br.com.homeoffice.apigerirpessoas.domain.PessoaEndereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    @Size(max = 150)
    private String logradouro;
    @NotBlank
    @Size(max = 15)
    private String numero;
    @Size(max = 15)
    private String complemento;
    private Cidade cidade ;

    private Set<PessoaEndereco> enderecos = new HashSet<>();
}
