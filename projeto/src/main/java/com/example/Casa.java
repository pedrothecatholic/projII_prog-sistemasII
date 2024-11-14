package com.example;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Casa")
public class Casa extends Imovel{
    private boolean quintal;
    private boolean garagem;

    public Casa(){
        super();
    }

    public Casa(String endereco, double areaUtil, int quartos, double precoAluguel, boolean disponibilidade, boolean quintal, boolean garagem){
        super(endereco, areaUtil, quartos, precoAluguel, disponibilidade);
        this.quintal = quintal;
        this.garagem = garagem;
    }

    public void setQuintal(boolean quintal){
        this.quintal = quintal;
    }

    public boolean getQuintal(){
        return quintal;
    }

    public void setGaragem(boolean garagem){
        this.garagem = garagem;
    }

    public boolean getGaragem(){
        return garagem;
    } 
}
