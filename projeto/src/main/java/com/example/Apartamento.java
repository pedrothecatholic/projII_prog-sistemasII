package com.example;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Apartamento") 
public class Apartamento extends Imovel{
    private int andar;
    private double condominio;

    public Apartamento(){
        super();
    }

    public Apartamento(String endereco, double areaUtil, int quartos, double precoAluguel, boolean disponibilidade, int andar, double condominio){
        super(endereco, areaUtil, quartos, precoAluguel, disponibilidade);
        this.andar = andar;
        this.condominio = condominio;
    }

    public void setAndar(int andar){
        this.andar = andar;
    }

    public int getAndar(){
        return andar;
    }

    public void setCondominio(double condominio){
        this.condominio = condominio;
    }

    public double getCondominio(){
        return condominio;
    }
}
