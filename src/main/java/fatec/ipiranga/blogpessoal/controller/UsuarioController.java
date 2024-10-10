package fatec.ipiranga.blogpessoal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.ipiranga.blogpessoal.model.Usuario;
import fatec.ipiranga.blogpessoal.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins="*",allowedHeaders="*")

public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    public ResponseEntity<Usuario> post (@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
    }
}

