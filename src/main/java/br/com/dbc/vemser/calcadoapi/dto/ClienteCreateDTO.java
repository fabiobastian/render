package br.com.dbc.vemser.calcadoapi.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ClienteCreateDTO {
    @NotEmpty
    private String nome;
    @Email
    private String email;
    @NotEmpty
    private String telefone;
}
