package com.fipe.tabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo(
    @JsonAlias("valor") String valor,
    @JsonAlias("marca") String marca,
    @JsonAlias("modelo") String modelo,
    @JsonAlias("anoModelo") Integer anoModelo,
    @JsonAlias("combustivel") String combustivel) {
}
