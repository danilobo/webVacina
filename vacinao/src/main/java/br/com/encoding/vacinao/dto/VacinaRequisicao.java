package br.com.encoding.vacinao.dto;

import br.com.encoding.vacinao.models.Vacina;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VacinaRequisicao {
    @NotBlank
    @NotNull
    private String nome;
    @NotBlank
    @NotNull
    private String doenca;

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

    public Vacina toVacina() {
        Vacina vacina = new Vacina();
        vacina.setNome(this.nome);
        vacina.setDoenca(this.doenca);
        return vacina;
    }

    public void fromVacina(Vacina vacina) {
        this.nome = vacina.getNome();
        this.doenca = vacina.getDoenca();
    }
    @Override
    public String toString() {
        return "VacinaRequisicao{" +
                "nome='" + nome + '\'' +
                ", doenca='" + doenca + '\'' +
                '}';
    }
}
