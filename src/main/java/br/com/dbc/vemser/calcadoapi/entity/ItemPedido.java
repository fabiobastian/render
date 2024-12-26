package br.com.dbc.vemser.calcadoapi.entity;

import lombok.*;
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {
    private Integer idItemPedido;
    private Integer idPedido;
    private Integer idProduto;
    private int quantidade;
}
