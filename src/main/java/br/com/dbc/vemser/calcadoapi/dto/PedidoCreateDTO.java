package br.com.dbc.vemser.calcadoapi.dto;

import br.com.dbc.vemser.calcadoapi.entity.StatusPedido;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PedidoCreateDTO {

    @NotNull
    private Integer idCliente;
    @NotNull
    private StatusPedido statusPedido;
}