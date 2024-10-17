package fatec.ipiranga.lojaonline.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private long qtdEstoque;
    
    @ManyToMany(mappedBy = "carrinhoProdutos")
    private List<Pedido> listaPedidos;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public long getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(long qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }
}
