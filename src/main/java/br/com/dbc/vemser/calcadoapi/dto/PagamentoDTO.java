package br.com.dbc.vemser.calcadoapi.dto;

import lombok.Data;

@Data
public class PagamentoDTO extends PagamentoCreateDTO {

    private Double valor;
    private Integer idPagamento;
}
