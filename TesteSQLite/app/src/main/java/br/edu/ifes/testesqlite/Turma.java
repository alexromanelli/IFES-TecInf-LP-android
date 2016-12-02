package br.edu.ifes.testesqlite;

/**
 * Created by romanelli on 11/11/16.
 */

public class Turma {
    private long id;
    private String abreviacao;
    private String descricao;
    private int ano;
    private int semestre;

    public Turma(long id, String abreviacao, String descricao, int ano, int semestre) {
        this.abreviacao = abreviacao;
        this.ano = ano;
        this.descricao = descricao;
        this.id = id;
        this.semestre = semestre;
    }

    @Override
    public String toString() {
        return abreviacao + " | " + ano + "-" + semestre;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
}
