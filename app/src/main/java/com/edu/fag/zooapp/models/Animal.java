package com.edu.fag.zooapp.models;

import com.edu.fag.zooapp.Categoria;
import com.orm.SugarRecord;
import com.orm.dsl.MultiUnique;
import com.orm.dsl.Unique;

import java.util.Date;

//@MultiUnique("codigo, outro")
public class Animal extends SugarRecord {
    @Unique
    private int codigo;
    private String descricao;
    private Categoria categoria;
    private Date dtRegistro;
    private Date dtAtualizacao;
}
