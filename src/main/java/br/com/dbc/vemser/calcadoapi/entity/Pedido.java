package br.com.dbc.vemser.calcadoapi.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    private Integer idPedido;
    private Integer idCliente;
    private LocalDate data;
    private StatusPedido statusPedido;
    private List<ItemPedido> listaProdutos;
}
