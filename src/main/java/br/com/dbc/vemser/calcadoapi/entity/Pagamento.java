package br.com.dbc.vemser.calcadoapi.entity;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {
    private Integer idPagamento;
    private Integer idPedido;
    private double valor;
    private FormaPagamento formaPagamento;
}
