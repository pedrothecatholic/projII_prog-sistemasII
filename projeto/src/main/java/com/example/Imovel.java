package com.example;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@Table(name="imoveis")
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "tipo"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Casa.class, name = "Casa"),
    @JsonSubTypes.Type(value = Apartamento.class, name = "Apartamento")
})

public abstract class Imovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String endereco;
    private double areaUtil;
    private int quartos;
    private double precoAluguel;
    private boolean disponibilidade;

  
    public Imovel() {}

    public Imovel(String endereco, double areaUtil, int quartos, double precoAluguel, boolean disponibilidade) {
        this.endereco = endereco;
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
