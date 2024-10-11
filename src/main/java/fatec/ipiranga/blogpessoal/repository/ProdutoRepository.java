package fatec.ipiranga.blogpessoal.repository;

import fatec.ipiranga.blogpessoal.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    public List<Produto> findAllByNomeContainingIgnoreCase (String nome);
}