package br.com.dbc.vemser.calcadoapi.entity;

import lombok.Getter;

@Getter
public enum FormaPagamento {
    CARTAO_DEBITO("Cartão de Debito"),
    CARTAO_CREDITO("Cartão de Credito"),
    BOLETO("Boleto Bancario"),
    DINHEIRO("Dinheiro"),
    PIX("PIX");

    private final String descricao;

    FormaPagamento(String descricao) {
        this.descricao = descricao;
    }
}
