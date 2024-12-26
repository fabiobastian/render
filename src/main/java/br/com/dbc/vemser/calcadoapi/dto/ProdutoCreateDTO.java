package br.com.dbc.vemser.calcadoapi.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class ProdutoCreateDTO {

    @NotEmpty
    @Size(max = 100)
    private String nome;
    @NotEmpty
    @Size(max = 100)
    private String descricao;
    @Positive
    private double preco;
    @Positive
    private double tamanho;
    @NotEmpty
    private String cor;
    @Positive
    private int estoque;
}
