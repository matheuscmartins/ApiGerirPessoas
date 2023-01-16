package br.com.homeoffice.apigerirpessoas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private Integer numero;
    private String complemento;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade ;

    @JsonIgnore
    @OneToMany(mappedBy = "endereco")
    private Set<PessoaEndereco> enderecos = new HashSet<>();
}
