package com.edu.fag.zooapp.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@MultiUnique("codigo, descricao")//Comentário teste Commit
public class Animal extends SugarRecord implements Serializable {
    @Unique
    private int codigo;
    private String descricao;
    private Categoria categoria;
    private Date dtRegistro;
    private Date dtAtualizacao;
    private List<Vacina> vacinaList = new ArrayList<Vacina>();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Date getDtRegistro() {
        return dtRegistro;
    }

    public void setDtRegistro(Date dtRegistro) {
        this.dtRegistro = dtRegistro;
    }

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    public List<Vacina> getVacinaList() {
        vacinaList = Vacina.find(Vacina.class, " animal = "+getId());
        return vacinaList;
    }

    @Override
    public String toString() {
        return descricao+" "+categoria.getDescricao();
    }
}
