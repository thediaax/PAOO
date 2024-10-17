package fatec.ipiranga.lojaonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fatec.ipiranga.lojaonline.model.Pedido;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	public List<Pedido> findByClienteId(long idCliente);
	public List<Pedido> findByDate(LocalDate dataPedido);
}