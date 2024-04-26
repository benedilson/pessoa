package br.com.pessoa.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 150, nullable = false)
    private String nome;

    @Temporal(TemporalType.DATE)
    private Date idade;

    @Column(length = 2)
    private String sexo;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Endereco> enderecos;

    public Endereco getEnderecoPrincipal() {
        return enderecos.isEmpty() ? null : enderecos.get(0);
    }
}
