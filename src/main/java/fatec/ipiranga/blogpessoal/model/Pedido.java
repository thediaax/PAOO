package fatec.ipiranga.blogpessoal.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long idUsuario;
    @ElementCollection
    private List<String> carrinhoProdutos;
    private LocalDate date;
    private long precoTotal;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<String> getCarrinhoProdutos() {
        return carrinhoProdutos;
    }

    public void setCarrinhoProdutos(List<String> carrinhoProdutos) {
        this.carrinhoProdutos = carrinhoProdutos;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(long precoTotal) {
        this.precoTotal = precoTotal;
    }
}
