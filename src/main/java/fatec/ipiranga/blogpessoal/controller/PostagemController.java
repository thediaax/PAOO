package fatec.ipiranga.blogpessoal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.ipiranga.blogpessoal.model.Postagem;
import fatec.ipiranga.blogpessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagem")
@CrossOrigin(origins="*",allowedHeaders="*")

public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	@PostMapping
	public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
}
