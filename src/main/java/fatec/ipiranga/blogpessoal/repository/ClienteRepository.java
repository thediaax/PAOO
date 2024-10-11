package fatec.ipiranga.blogpessoal.repository;

import fatec.ipiranga.blogpessoal.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public List<Cliente> findAllByNomeContainingIgnoreCase (String nome);
}