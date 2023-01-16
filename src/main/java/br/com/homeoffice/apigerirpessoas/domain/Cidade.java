package br.com.homeoffice.apigerirpessoas.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb cidade")
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String nome;
    private String uf;

    @JsonIgnore
    @OneToMany(mappedBy = "cidade")
    private Set<Endereco> enderecos = new HashSet<>();
}
