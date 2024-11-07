package br.com.pior.filme.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.pior.filme.models.Produtor;

public interface ProdutorRepository extends CrudRepository<Produtor, Integer> {
}