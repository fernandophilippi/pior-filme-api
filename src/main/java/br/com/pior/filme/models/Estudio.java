package br.com.pior.filme.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "TB_ESTUDIO")
@Entity
@Builder
public class Estudio {

    @Id
    @GeneratedValue
    @Column(name = "ID_ESTUDO", nullable = false)
    private int id;

    @Column(name = "NM_ESTUDIO", nullable = false, length = 100)
    private String nome;

}
