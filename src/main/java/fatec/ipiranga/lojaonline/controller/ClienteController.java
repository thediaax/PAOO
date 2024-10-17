package fatec.ipiranga.lojaonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fatec.ipiranga.lojaonline.model.Cliente;
import fatec.ipiranga.lojaonline.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

 
    @PostMapping
    public ResponseEntity<Cliente> post(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(cliente));
    }
 
    @GetMapping
    public ResponseEntity<List<Cliente>> GetAll() {
        return ResponseEntity.ok(repository.findAll());
    }
 
    @GetMapping("id/{id}")
    public ResponseEntity<Cliente> GetById(@PathVariable("id") long id) {
        Optional<Cliente> cliente = repository.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Cliente>> GetByNome(@PathVariable("nome") String nome){
    	List<Cliente> clientes = repository.findAllByNomeContainingIgnoreCase(nome);
    	
    	if(clientes.isEmpty()) {
    		return ResponseEntity.notFound().build();
    	}
    	else {
    		return ResponseEntity.ok(clientes);
    	}
    }

     @PutMapping("/id/{id}")
    public ResponseEntity<Cliente> update(@PathVariable("id") long id, @RequestBody Cliente cliente) {
        return repository.findById(id)
            .map(clienteExistente -> {
                clienteExistente.setNome(cliente.getNome());
                clienteExistente.setEmail(cliente.getEmail());
                clienteExistente.setSenha(cliente.getSenha());
                clienteExistente.setCpf(cliente.getCpf());
                return ResponseEntity.ok(repository.save(clienteExistente));
            })
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

     @DeleteMapping("/id/{id}")
 	public ResponseEntity<String> DeleteById(@PathVariable("id") long id) {
 	    if (repository.existsById(id)) {
 	        repository.deleteById(id);
 	        return ResponseEntity.ok("Cliente com ID " + id + " foi excluído com sucesso.");
 	    } else {
 	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente com ID " + id + " não encontrado.");
 	    }
 	}
}
