package fatec.ipiranga.lojaonline.controller;

import java.util.List;
import java.util.Optional;

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

import fatec.ipiranga.lojaonline.model.Produto;
import fatec.ipiranga.lojaonline.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;
	
	@PostMapping
	public ResponseEntity<Produto> post (@RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}
	
	@GetMapping
	public ResponseEntity<List<Produto>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Produto> GetById(@PathVariable("id") long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> GetByNome(@PathVariable("nome") String nome){
		List<Produto> produtos = repository.findAllByNomeContainingIgnoreCase(nome);
		
		if(produtos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(produtos);			
		}
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<Produto> UpdateById(@PathVariable("id") long id, @RequestBody Produto produtoAtualizado){
		return repository.findById(id)
				.map(produto -> {
					Optional.ofNullable(produtoAtualizado.getNome()).ifPresent(produto::setNome);
		            Optional.ofNullable(produtoAtualizado.getDescricao()).ifPresent(produto::setDescricao);
		            Optional.ofNullable(produtoAtualizado.getPreco()).ifPresent(produto::setPreco);
		            Optional.ofNullable(produtoAtualizado.getQtdEstoque()).ifPresent(produto::setQtdEstoque);
					
					return ResponseEntity.ok(repository.save(produto));
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<String> DeleteById(@PathVariable("id") long id) {
	    if (repository.existsById(id)) {
	        repository.deleteById(id);
	        return ResponseEntity.ok("Produto com ID " + id + " foi excluído com sucesso.");
	    } else {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto com ID " + id + " não encontrado.");
	    }
	}
	
	
}


