package fatec.ipiranga.lojaonline.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="pedido")

public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    @JoinColumn(name="id_cliente", referencedColumnName="id", nullable=false)
    private Cliente cliente;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
    	name = "pedido_produto",
    	joinColumns = @JoinColumn(name="pedido_id"),
    	inverseJoinColumns = @JoinColumn(name="produto_id")
    )
    private List<Produto> carrinhoProdutos;
    
    private LocalDate date = LocalDate.now();
    private BigDecimal precoTotal = BigDecimal.ZERO;
    
    public void adicionarProduto(Produto produto) {
    	carrinhoProdutos.add(produto);
    	precoTotal = precoTotal.add(produto.getPreco());
    }
    
    public void removerProduto(Produto produtoDeletar) {
    	carrinhoProdutos.removeIf(produto -> produto.getId() == produtoDeletar.getId());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getCarrinhoProdutos() {
        return carrinhoProdutos;
    }

    public void setCarrinhoProdutos(List<Produto> carrinhoProdutos) {
        this.carrinhoProdutos = carrinhoProdutos;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }
}
