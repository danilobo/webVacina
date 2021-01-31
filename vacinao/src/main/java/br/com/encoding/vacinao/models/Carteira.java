package br.com.encoding.vacinao.models;

import javax.persistence.*;

@Entity
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long idUsuario;
    private Long idVacinas;
    private String aplicacaoData;

    public Carteira() {
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdVacinas() {
        return idVacinas;
    }

    public void setIdVacinas(Long idVacinas) {
        this.idVacinas = idVacinas;
    }

    public String getAplicacaoData() {
        return aplicacaoData;
    }

    public void setAplicacaoData(String aplicacaoData) {
        this.aplicacaoData = aplicacaoData;
    }
}
