package fatec.ipiranga.blogpessoal.repository;

import fatec.ipiranga.blogpessoal.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	public List<Pedido> findByClienteId(long idCliente);
	public List<Pedido> findByDate(LocalDate dataPedido);
}