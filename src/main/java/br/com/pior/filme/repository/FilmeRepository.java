package br.com.pior.filme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.pior.filme.models.Filme;
import br.com.pior.filme.models.dto.ProdutorRetornoDTO;

public interface FilmeRepository extends CrudRepository<Filme, Integer> {
    
    @Query("SELECT new br.com.pior.filme.models.dto.ProdutorRetornoDTO("
            + "        p.nome,"
            + "        f.ano AS previousWin,"
            + "        f.ano AS followingWin, "
            + "        0 AS interval "
            + "    )"
            + "    FROM Filme f"
            + "    JOIN f.produtores p"
            + "    WHERE f.vencedor = 1"
            + "    ORDER BY p.nome, f.ano")
     List<ProdutorRetornoDTO> findProducerIntervals();

    
}
