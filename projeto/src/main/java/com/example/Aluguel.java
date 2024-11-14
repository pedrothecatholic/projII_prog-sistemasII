package com.example;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="alugueis")
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "imovel_id", nullable = false)
    private Imovel imovel;

    @ManyToOne
    @JoinColumn(name = "inquilino_id", nullable = false)
    private Inquilino inquilino;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date data_inicio;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date data_fim;

    public Aluguel() {}

    public Aluguel(Imovel imovel, Inquilino inquilino) {
        this.imovel = imovel;
        this.inquilino = inquilino;
    }

   
    public Long getId() {
        return id;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    @JsonProperty("data_inicio")
    public Date getDataInicio (){
        return data_inicio;
    }

    public void setDataInicio(Date data_inicio){
        this.data_inicio = data_inicio;
    }

    @JsonProperty("data_fim")
    public Date getDataFim(){
        return data_fim;
    }
    public void setDataFim(Date data_fim){
        this.data_fim = data_fim;
    }
}
