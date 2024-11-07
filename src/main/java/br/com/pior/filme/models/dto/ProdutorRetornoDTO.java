package br.com.pior.filme.models.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class ProdutorRetornoDTO {

    private String producer;
    private int interval;
    private int previousWin;
    private int followingWin;

    public ProdutorRetornoDTO(String producer, int previousWin, int followingWin, int interval) {
        super();
        this.producer = producer;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
        this.interval = interval;
    }

}
