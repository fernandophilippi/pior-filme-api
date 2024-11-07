package br.com.pior.filme.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pior.filme.models.dto.IntervaloPremiosDTO;
import br.com.pior.filme.service.FilmeService;

@RestController
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping("/filmes")
    public IntervaloPremiosDTO filmes() {

        return filmeService.getIntervaloPremios();

    }

}
