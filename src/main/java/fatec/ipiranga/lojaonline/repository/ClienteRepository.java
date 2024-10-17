package fatec.ipiranga.lojaonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fatec.ipiranga.lojaonline.model.Cliente;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public List<Cliente> findAllByNomeContainingIgnoreCase (String nome);
}