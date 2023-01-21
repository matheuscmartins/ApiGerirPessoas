package br.com.homeoffice.apigerirpessoas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
@Table(name = "tb_endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 150)
    @NotEmpty(message = "O campo logradouro é obrigatorio")
    private String logradouro;
    @Column(nullable = false, length = 15)
    @NotEmpty(message = "O campo numero é obrigatorio")
    private String numero;
    @Column(nullable = true, length = 15)
    private String complemento;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade ;


    @OneToMany(mappedBy = "endereco")
    @JsonIgnore
    private Set<PessoaEndereco> enderecos = new HashSet<>();
}
