package fatec.ipiranga.blogpessoal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.ipiranga.blogpessoal.model.Cliente;
import fatec.ipiranga.blogpessoal.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins="*",allowedHeaders="*")

public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    public ResponseEntity<Cliente> post (@RequestBody Cliente cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(cliente));
    }
}

