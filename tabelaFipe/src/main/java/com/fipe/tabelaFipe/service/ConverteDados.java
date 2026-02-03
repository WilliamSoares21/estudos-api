package com.fipe.tabelaFipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class ConverteDados implements IConverteDados {
  private ObjectMapper mapper = new ObjectMapper();

  @Override
  public <T> T obterDados(String json, Class<T> classe) {
    try {
      return mapper.readValue(json, classe);
    } catch (JsonProcessingException e) {
      throw new RuntimeException();
    }
  }

  public <T> List<T> obterLista(String json, TypeReference<List<T>> typeReference) {
    try {
      return mapper.readValue(json, typeReference);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
