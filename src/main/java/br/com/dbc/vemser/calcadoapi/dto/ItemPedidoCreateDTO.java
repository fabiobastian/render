package br.com.dbc.vemser.calcadoapi.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class ItemPedidoCreateDTO {
    @NotNull
    private Integer idPedido;
    @NotNull
    private Integer idProduto;
    @Positive
    private int quantidade;
}
