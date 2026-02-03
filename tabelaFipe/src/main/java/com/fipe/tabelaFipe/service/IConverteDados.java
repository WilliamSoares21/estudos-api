package com.fipe.tabelaFipe.service;

public interface IConverteDados {
  <T> T obterDados(String json, Class<T> classe);
}
