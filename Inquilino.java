import javax.persistence.*;
import java.util.List;

@Entity
public class Inquilino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String senha;

    @OneToMany(mappedBy = "inquilino", cascade = CascadeType.ALL)
    private List<Aluguel> alugueis;

    // Construtor padrão
    public Inquilino() {}

    // Construtor com parâmetros
    public Inquilino(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Aluguel> getAlugueis() {
        return alugueis;
    }

    public void setAlugueis(List<Aluguel> alugueis) {
        this.alugueis = alugueis;
    }
}
