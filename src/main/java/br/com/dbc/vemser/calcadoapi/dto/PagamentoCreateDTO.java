package br.com.dbc.vemser.calcadoapi.dto;

import br.com.dbc.vemser.calcadoapi.entity.FormaPagamento;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PagamentoCreateDTO {

    @NotNull
    private Integer idPedido;
    @NotNull
    private FormaPagamento formaPagamento;
}
