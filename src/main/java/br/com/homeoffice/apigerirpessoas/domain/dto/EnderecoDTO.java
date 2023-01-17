package br.com.homeoffice.apigerirpessoas.domain.dto;

import br.com.homeoffice.apigerirpessoas.domain.Cidade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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



}
