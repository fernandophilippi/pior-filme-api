package br.com.pior.filme.models.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IntervaloPremiosDTO {

    private List<ProdutorRetornoDTO> min;
    private List<ProdutorRetornoDTO> max;

}
