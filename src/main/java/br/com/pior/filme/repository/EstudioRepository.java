package br.com.pior.filme.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.pior.filme.models.Estudio;

public interface EstudioRepository extends CrudRepository<Estudio, Integer> {
}