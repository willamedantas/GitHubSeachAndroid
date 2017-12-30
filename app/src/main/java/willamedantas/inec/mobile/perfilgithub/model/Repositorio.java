package willamedantas.inec.mobile.perfilgithub.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Classe de modelo para definir os campos de repositorio.
 * Created by Willame Dantas.
 */

public class Repositorio implements Serializable {

    public Repositorio(){

    }

    public Repositorio(String nomeRepositorio, String descricao, String dataCriacao, String ultimaAtualizacao, String languagemProjeto) {
        this.nomeRepositorio = nomeRepositorio;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.ultimaAtualizacao = ultimaAtualizacao;
        this.languagemProjeto = languagemProjeto;
    }

    @SerializedName("name")
    private String nomeRepositorio;

    @SerializedName("description")
    private String descricao;

    @SerializedName("created_at")
    private String dataCriacao;

    @SerializedName("updated_at")
    private String ultimaAtualizacao;

    @SerializedName("language")
    private String languagemProjeto;

    public String getNomeRepositorio() {
        return nomeRepositorio;
    }

    public void setNomeRepositorio(String nomeRepositorio) {
        this.nomeRepositorio = nomeRepositorio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(String ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public String getLanguagemProjeto() {
        return languagemProjeto;
    }

    public void setLanguagemProjeto(String languagemProjeto) {
        this.languagemProjeto = languagemProjeto;
    }
}
