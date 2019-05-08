package com.edu.fag.zooapp;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

public class Categoria extends SugarRecord {
    @Unique
    private int codigo;
    private String descricao;
    private boolean ativo;

    public Categoria() {
    }

    public Categoria(int codigo, String descricao, boolean ativo) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.ativo = ativo;
    }

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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return codigo + " - "+ descricao;
    }
}
