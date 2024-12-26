package br.com.dbc.vemser.calcadoapi.entity;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    private Integer idProduto;
    private String nome;
    private String descricao;
    private double preco;
    private double tamanho;
    private String cor;
    private int estoque;
}
