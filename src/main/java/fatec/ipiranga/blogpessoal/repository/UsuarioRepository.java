package fatec.ipiranga.blogpessoal.repository;

import java.util.List;

import fatec.ipiranga.blogpessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.ipiranga.blogpessoal.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    public List<Usuario> findAllByTituloContainingIgnoreCase(String titulo);
}

