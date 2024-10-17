package fatec.ipiranga.lojaonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fatec.ipiranga.lojaonline.model.Produto;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    public List<Produto> findAllByNomeContainingIgnoreCase (String nome);
}