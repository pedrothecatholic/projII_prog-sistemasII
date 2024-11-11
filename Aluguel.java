import javax.persistence.*;

@Entity
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "imovel_id")
    private Imovel imovel;

    @ManyToOne
    @JoinColumn(name = "inquilino_id")
    private Inquilino inquilino;

    // Construtor padrão
    public Aluguel() {}

    // Construtor com parâmetros
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
}
