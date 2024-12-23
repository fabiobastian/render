package br.com.dbc.vemser.calcadoapi.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class ProdutoCreateDTO {

    @NotEmpty
    @Size(max = 100)
    private String nome;
    @NotEmpty
    @Size(max = 100)
    private String descricao;
    @NotNull
    private double preco;
    @NotNull
    private float tamanho;
    @NotEmpty
    private String cor;
    @NotNull
    private int estoque;
}
