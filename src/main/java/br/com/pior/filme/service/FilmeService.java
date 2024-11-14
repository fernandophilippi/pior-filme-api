package br.com.pior.filme.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import br.com.pior.filme.models.Estudio;
import br.com.pior.filme.models.Filme;
import br.com.pior.filme.models.Produtor;
import br.com.pior.filme.models.dto.IntervaloPremiosDTO;
import br.com.pior.filme.models.dto.ProdutorRetornoDTO;
import br.com.pior.filme.repository.EstudioRepository;
import br.com.pior.filme.repository.FilmeRepository;
import br.com.pior.filme.repository.ProdutorRepository;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private ProdutorRepository produtorRepository;

    @Autowired
    private EstudioRepository estudioRepository;

    @Value("${app.arquivo}")
    private String arquivo;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    private void postConstruct() {

        String separador = ";";
        int j = 0;

        Map<String, Estudio> mapEstudio = new HashMap<String, Estudio>();
        Map<String, Produtor> mapProdutor = new HashMap<String, Produtor>();

        Resource resource = resourceLoader.getResource(arquivo);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String linha;
            while ((linha = reader.readLine()) != null) {

                if (j == 0) {
                    j++;
                    continue;
                }

                linha = new String(linha.getBytes(), "UTF-8");

                String[] valores = linha.split(separador);

                if (valores.length >= 4) {
                    int ano = Integer.valueOf(valores[0]);
                    String titulo = valores[1];
                    String estudiosNome = valores[2];
                    String produtoresNome = valores[3];
                    boolean vencedor = false;
                    if (valores.length > 4) {
                        vencedor = "yes".equals(valores[4]);
                    }

                    List<Estudio> estudios = new ArrayList<Estudio>();
                    String[] estudiosArray = estudiosNome.split(",");
                    for (String estu : estudiosArray) {

                        estu = estu.trim().toUpperCase();

                        Estudio estudio = null;
                        if (mapEstudio.containsKey(estu)) {
                            estudio = mapEstudio.get(estu);
                        } else {
                            estudio = estudioRepository.save(Estudio.builder().nome(estu).build());
                            mapEstudio.put(estu, estudio);
                        }

                        estudios.add(estudio);

                    }

                    List<Produtor> produtores = new ArrayList<Produtor>();
                    String[] produtoresArray = produtoresNome.split(",|and");
                    for (String prod : produtoresArray) {

                        prod = prod.trim().toUpperCase();

                        Produtor produtor = null;
                        if (mapProdutor.containsKey(prod)) {
                            produtor = mapProdutor.get(prod);
                        } else {
                            produtor = produtorRepository.save(Produtor.builder().nome(prod).build());
                            mapProdutor.put(prod, produtor);
                        }

                        produtores.add(produtor);

                    }

                    Filme filme = new Filme();
                    filme.setAno(ano);
                    filme.setTitulo(titulo);
                    filme.setVencedor(vencedor ? 1 : 0);
                    filme.setEstudios(estudios);
                    filme.setProdutores(produtores);
                    filmeRepository.save(filme);

                }

                j++;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public IntervaloPremiosDTO getIntervaloPremios() {

        List<ProdutorRetornoDTO> resultados = filmeRepository.findProducerIntervals();

        Map<String, List<ProdutorRetornoDTO>> intervalosPorProdutor = resultados.stream()
                .collect(Collectors.groupingBy(ProdutorRetornoDTO::getProducer));

        List<ProdutorRetornoDTO> allIntervals = new ArrayList<>();

        for (List<ProdutorRetornoDTO> produtorIntervalos : intervalosPorProdutor.values()) {

            produtorIntervalos.sort(Comparator.comparingInt(ProdutorRetornoDTO::getPreviousWin));

            if (produtorIntervalos.size() > 1) {

                for (int i = 0; i < produtorIntervalos.size() - 1; i++) {
                    int previousWin = produtorIntervalos.get(i).getPreviousWin();
                    int followingWin = produtorIntervalos.get(i + 1).getPreviousWin();
                    int interval = followingWin - previousWin;

                    allIntervals.add(new ProdutorRetornoDTO(produtorIntervalos.get(i).getProducer(), previousWin,
                            followingWin, interval));
                }
            }
        }

        IntervaloPremiosDTO intervaloPremiosDTO = IntervaloPremiosDTO.builder().min(allIntervals.stream()
                .filter(dto -> dto.getInterval() == Collections
                        .min(allIntervals.stream().map(ProdutorRetornoDTO::getInterval).collect(Collectors.toList())))
                .collect(Collectors.toList()))
                .max(allIntervals.stream()
                        .filter(dto -> dto.getInterval() == Collections.max(allIntervals.stream()
                                .map(ProdutorRetornoDTO::getInterval).collect(Collectors.toList())))
                        .collect(Collectors.toList()))
                .build();

        return intervaloPremiosDTO;
    }

}
