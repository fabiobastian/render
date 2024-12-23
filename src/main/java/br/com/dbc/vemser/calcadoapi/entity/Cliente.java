package br.com.dbc.vemser.calcadoapi.entity;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private Integer idCliente;
    private String nome;
    private String email;
    private String telefone;
}
