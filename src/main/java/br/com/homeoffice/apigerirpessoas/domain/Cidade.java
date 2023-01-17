package br.com.homeoffice.apigerirpessoas.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_cidade")
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(nullable = false, length = 150)
    @NotEmpty(message = "O campo nome da cidade é obrigatorio")
    private String nome;
    @Column(nullable = false, length = 2)
    @NotEmpty(message = "O campo de UF é obrigatorio")
    private String uf;

}
