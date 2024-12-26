package br.com.dbc.vemser.calcadoapi.entity;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private Integer idEndereco;
    private Integer idCliente;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String cep;
}
