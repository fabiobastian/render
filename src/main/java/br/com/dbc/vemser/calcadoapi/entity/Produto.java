package br.com.dbc.vemser.calcadoapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    private Integer idProduto;
    private String nome;
    private String descricao;
    private double preco;
    private float tamanho;
    private String cor;
    private int estoque;
}
