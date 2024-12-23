package br.com.dbc.vemser.calcadoapi.dto;

import br.com.dbc.vemser.calcadoapi.entity.StatusPedido;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class PedidoCreateDTO {

    @NotNull
    private Integer idPagamento;
    @NotNull
    private double total;
    @NotEmpty
    private StatusPedido statusPedido;
}
