package br.com.dbc.vemser.calcadoapi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PedidoDTO extends PedidoCreateDTO {

    private Integer idPedido;
    private Double valor;
    private LocalDate data;
}
