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
@Table(name = "TB_PRODUTOR")
@Entity
@Builder
public class Produtor {

    @Id
    @GeneratedValue
    @Column(name = "ID_PRODUTOR", nullable = false)
    private int idProdutor;

    @Column(name = "NM_PRODUTOR", nullable = false, length = 100)
    private String nome;

}
