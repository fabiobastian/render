package br.com.dbc.vemser.calcadoapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {
    private Integer idPagamento;
    private double valor;
    private FormaPagamento formaPagamento;
}
