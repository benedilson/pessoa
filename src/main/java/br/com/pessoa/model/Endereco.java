package br.com.pessoa.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 2)
    private String estado;

    @Column(length = 100)
    private String cidade;

    @Column(length = 100)
    private String logradouro;

    @Column(length = 100)
    private String bairro;

    private Integer numero;

    @Column(length = 8)
    private String cep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;
}
