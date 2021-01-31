package br.com.encoding.vacinao.models;

import com.sun.istack.NotNull;
import org.hibernate.annotations.NotFound;

import javax.persistence.*;

@Entity
public class Vacina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    private String doenca;

    public Vacina(String nome, String doenca) {
        this.nome = nome;
        this.doenca = doenca;
    }

    public Vacina() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDoenca() {
        return doenca;
    }

    public void setDoenca(String doenca) {
        this.doenca = doenca;
    }
}
