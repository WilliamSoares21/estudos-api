package com.cep;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileWriter {
  public static void gravarEnderecoEmJson(List<EnderecoDTO> listaDeEnderecos) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    File enderecoJson = new File("cep_consultados.json");
    mapper.writeValue(enderecoJson, listaDeEnderecos);

    System.out.println("Dados gravados com sucesso em: " + enderecoJson.getAbsolutePath());
  }
}
