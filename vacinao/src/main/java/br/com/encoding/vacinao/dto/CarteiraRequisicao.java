package br.com.encoding.vacinao.dto;

import br.com.encoding.vacinao.models.Carteira;
import br.com.encoding.vacinao.models.Vacina;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraRequisicao {
    @NotNull
    private Long idUsuario;
    @NotNull
    private Long idVacinas;
    @NotNull
    private String aplicacaoData;

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

    public Carteira toCarteria() {
        Carteira carteira = new Carteira();
        carteira.setIdUsuario(this.idUsuario);
        carteira.setIdVacinas(this.idVacinas);
        carteira.setAplicacaoData(this.aplicacaoData);
        return carteira;
    }

    public void fromCarteira(Carteira carteira) {
        this.idUsuario = carteira.getIdUsuario();
        this.idVacinas = carteira.getIdVacinas();
        this.aplicacaoData = carteira.getAplicacaoData();
    }

    @Override
    public String toString() {
        return "CarteiraRequisicao{" +
                "idUsuario=" + idUsuario +
                ", idVacinas=" + idVacinas +
                ", aplicacaoData='" + aplicacaoData + '\'' +
                '}';
    }
}
