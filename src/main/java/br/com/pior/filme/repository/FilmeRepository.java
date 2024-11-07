package br.com.pior.filme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.pior.filme.models.Filme;
import br.com.pior.filme.models.dto.ProdutorRetornoDTO;

public interface FilmeRepository extends CrudRepository<Filme, Integer> {
    
    @Query("SELECT new br.com.pior.filme.models.dto.ProdutorRetornoDTO(" +
            "p.nome, " +
            "MIN(f2.ano), " +
            "f1.ano, " +
            "(f1.ano - MIN(f2.ano))) " +
            "FROM Filme f1, Filme f2, Produtor p, Produtor p2 " +
            "WHERE f1.vencedor = 1 " +
            "AND f2.vencedor = 1 " +
            "AND p MEMBER OF f1.produtores " +
            "AND p2 MEMBER OF f2.produtores " +
            "AND p.idProdutor = p2.idProdutor " +
            "AND f1.ano > f2.ano " +
            "GROUP BY p.nome, f1.ano " +
            "ORDER BY (f1.ano - MIN(f2.ano)) DESC")
     List<ProdutorRetornoDTO> findProducerIntervals();
    
}
