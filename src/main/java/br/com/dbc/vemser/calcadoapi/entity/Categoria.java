package br.com.dbc.vemser.calcadoapi.entity;

import lombok.Getter;

@Getter
public enum Categoria {
    TENIS("Tênis"),
    SANDALIA("Sandália"),
    SAPATO_SOCIAL("Sapato Social"),
    CHINELO("Chinelo"),
    BOTAS("Botas"),
    SAPATENIS("Sapatênis"),
    MOCASSIM("Mocassim"),
    SCARPIN("Scarpin"),
    ANABELA("Anabela"),
    RASTEIRINHA("Rasteirinha"),
    COTURNO("Coturno"),
    ESPORTE("Esporte"),
    CASUAL("Casual"),
    SOCIAL("Social"),
    INFANTIL("Infantil");

    private final String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }
}
