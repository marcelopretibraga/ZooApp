package com.edu.fag.zooapp.models;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class Vacina extends SugarRecord implements Serializable {
    @Unique
    private int registro;
    private String dsVacina;
    private Animal animal;
    private Date dtVacina;
    private Double psAnimal;
    private Double qtVacina;
    private String dsObservacao;

    @Ignore //Não cria o campo na tabela
    private int nrControleInterno;

    public Vacina() {
    }

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

    public String getDsVacina() {
        return dsVacina;
    }

    public void setDsVacina(String dsVacina) {
        this.dsVacina = dsVacina;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Date getDtVacina() {
        return dtVacina;
    }

    public void setDtVacina(Date dtVacina) {
        this.dtVacina = dtVacina;
    }

    public Double getPsAnimal() {
        return psAnimal;
    }

    public void setPsAnimal(Double psAnimal) {
        this.psAnimal = psAnimal;
    }

    public Double getQtVacina() {
        return qtVacina;
    }

    public void setQtVacina(Double qtVacina) {
        this.qtVacina = qtVacina;
    }

    public String getDsObservacao() {
        return dsObservacao;
    }

    public void setDsObservacao(String dsObservacao) {
        this.dsObservacao = dsObservacao;
    }

    public int getNrControleInterno() {
        return nrControleInterno;
    }

    public void setNrControleInterno(int nrControleInterno) {
        this.nrControleInterno = nrControleInterno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vacina)) return false;
        Vacina vacina = (Vacina) o;
        return registro == vacina.registro;
    }

    @Override
    public int hashCode() {
        return Objects.hash(registro);
    }

    @Override
    public String toString() {
        return registro +" - "+dsVacina +" : "+animal.getDescricao();
    }
}
