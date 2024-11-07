package br.com.pior.filme.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "TB_FILME")
@Entity
@Builder
public class Filme {

    @Id
    @GeneratedValue
    @Column(name = "ID_FILME", nullable = false)
    private int id;

    @Column(name = "NU_ANO", nullable = false)
    private int ano;

    @Column(name = "NM_TITULO", nullable = false, length = 100)
    private String titulo;

    @Column(name = "FL_VENCEDOR", nullable = false)
    private int vencedor;

    @ManyToMany
    @JoinTable(name = "RL_FILME_ESTUDIO", joinColumns = @JoinColumn(name = "ID_FILME"), inverseJoinColumns = @JoinColumn(name = "ID_ESTUDIO"))
    private List<Estudio> estudios;

    @ManyToMany
    @JoinTable(name = "RL_FILME_PRODUTOR", joinColumns = @JoinColumn(name = "ID_FILME"), inverseJoinColumns = @JoinColumn(name = "ID_PRODUTOR"))
    private List<Produtor> produtores;

}
