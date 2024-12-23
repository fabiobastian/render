package br.com.dbc.vemser.calcadoapi.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EnderecoCreateDTO {

    @NotEmpty
    private String logradouro;
    @NotNull
    private String numero;
    @NotEmpty
    private String bairro;
    @NotEmpty
    private String cidade;
    @NotEmpty
    private String cep;
}
