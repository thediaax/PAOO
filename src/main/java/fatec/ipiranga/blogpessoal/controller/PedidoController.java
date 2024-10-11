package fatec.ipiranga.blogpessoal.controller;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.ipiranga.blogpessoal.model.Pedido;
import fatec.ipiranga.blogpessoal.repository.PedidoRepository;

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class PedidoController {
	@Autowired
	private PedidoRepository repository;
	
	@PostMapping
	public ResponseEntity<Pedido> post (@RequestBody Pedido produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}
	
	@GetMapping
	public ResponseEntity<List<Pedido>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Pedido> GetById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/idUsuario/{idUsuario}")
	public ResponseEntity<List<Pedido>> GetByIdUsuario(@PathVariable long idUsuario){
		List<Pedido> pedidos = repository.findByIdUsuario(idUsuario);
		
		if(pedidos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(pedidos);			
		}
	}
	
	@GetMapping("/dataPedido/{data}")
	public ResponseEntity<List<Pedido>> GetByDataPedido(@PathVariable String data){
		DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		try {
			LocalDate dataFormatada = LocalDate.parse(data, formatarData);
			List<Pedido> pedidos = repository.findByDate(dataFormatada);
			
			if(pedidos.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			else {
				return ResponseEntity.ok(pedidos);
			}
		}
		catch(DateTimeParseException error){
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<Pedido> UpdateById(@PathVariable long id, @RequestBody Pedido pedidoAtualizado){
		return repository.findById(id)
				.map(pedido -> {
					Optional.ofNullable(pedidoAtualizado.getCarrinhoProdutos()).ifPresent(pedido::setCarrinhoProdutos);
		            Optional.ofNullable(pedidoAtualizado.getDate()).ifPresent(pedido::setDate);
		            Optional.ofNullable(pedidoAtualizado.getPrecoTotal()).ifPresent(pedido::setPrecoTotal);
					
					return ResponseEntity.ok(repository.save(pedido));
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<String> DeleteById(@PathVariable long id) {
	    if (repository.existsById(id)) {
	        repository.deleteById(id);
	        return ResponseEntity.ok("Pedido com ID " + id + " foi excluído com sucesso.");
	    } else {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido com ID " + id + " não encontrado.");
	    }
	}
}
