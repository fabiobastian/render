package br.com.dbc.vemser.calcadoapi.dto;

import br.com.dbc.vemser.calcadoapi.entity.Cliente;
import lombok.Data;

@Data
public class EnderecoDTO extends EnderecoCreateDTO {
    private Integer idEndereco;
    private Integer idCliente;
}
