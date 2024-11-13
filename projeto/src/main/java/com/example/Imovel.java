package com.example;
import javax.persistence.*;

@Entity
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String endereco;
    private String tipo; 
    private double areaUtil;
    private int quartos;
    private double precoAluguel;
    private boolean disponibilidade;

  
    public Imovel() {}

    public Imovel(String endereco, String tipo, double areaUtil, int quartos, double precoAluguel, boolean disponibilidade) {
        this.endereco = endereco;
        this.tipo = tipo;
        this.areaUtil = areaUtil;
        this.quartos = quartos;
        this.precoAluguel = precoAluguel;
        this.disponibilidade = disponibilidade;
    }

  
    public Long getId() {
        return id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getAreaUtil() {
        return areaUtil;
    }

    public void setAreaUtil(double areaUtil) {
        this.areaUtil = areaUtil;
    }

    public int getQuartos() {
        return quartos;
    }

    public void setQuartos(int quartos) {
        this.quartos = quartos;
    }

    public double getPrecoAluguel() {
        return precoAluguel;
    }

    public void setPrecoAluguel(double precoAluguel) {
        this.precoAluguel = precoAluguel;
    }

    public boolean getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}
