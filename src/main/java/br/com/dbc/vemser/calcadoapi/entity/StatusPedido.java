package br.com.dbc.vemser.calcadoapi.entity;

import lombok.Getter;

@Getter
public enum StatusPedido {
    PENDENTE("Pedido pendente de confirmação"),
    CONFIRMADO("Pedido confirmado"),
    CANCELADO("Pedido cancelado"),
    EM_PROCESSAMENTO("Pedido em processamento"),
    FINALIZADO("Pedido finalizado"),

    EM_TRANSITO("Pedido em trânsito"),
    ENTREGUE("Pedido entregue"),
    ATRASADO("Entrega atrasada"),
    DEVOLVIDO("Pedido devolvido ao remetente");

    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }
}
