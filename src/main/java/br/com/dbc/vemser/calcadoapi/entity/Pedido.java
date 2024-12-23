package br.com.dbc.vemser.calcadoapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    private Integer idPedido;
    private Integer idPagamento;
    private LocalDate data;
    private double total;
    private StatusPedido statusPedido;
}
